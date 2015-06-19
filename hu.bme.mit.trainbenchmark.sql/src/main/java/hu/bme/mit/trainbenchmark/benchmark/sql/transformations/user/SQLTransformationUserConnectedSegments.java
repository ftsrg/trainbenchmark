/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user;

import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;

import java.util.Collection;

public class SQLTransformationUserConnectedSegments extends SQLTransformationUser {

	public SQLTransformationUserConnectedSegments(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> segments) {
		for (final Node segment1 : segments) {
			final Iterable<Relationship> sensors = segment1.getRelationships(Direction.OUTGOING, SQLConstants.relationshipTypeSensor);

			if (!sensors.iterator().hasNext()) {
				continue;
			}

			final Node sensor = sensors.iterator().next().getEndNode();
			final Iterable<Relationship> segment3s = segment1.getRelationships(Direction.OUTGOING,
					SQLConstants.relationshipTypeConnectsTo);

			if (!segment3s.iterator().hasNext()) {
				continue;
			}
			final Relationship connectsTo = segment3s.iterator().next();
			final Node segment3 = connectsTo.getEndNode();

			// transformation			
			connectsTo.delete();
			final Node segment2 = sqlDriver.getGraphDb().createNode(labelSegment);
			segment1.createRelationshipTo(segment2, relationshipTypeConnectsTo);
			segment2.createRelationshipTo(segment3, relationshipTypeConnectsTo);
			segment2.createRelationshipTo(sensor, relationshipTypeSensor);
		}
	}

}
