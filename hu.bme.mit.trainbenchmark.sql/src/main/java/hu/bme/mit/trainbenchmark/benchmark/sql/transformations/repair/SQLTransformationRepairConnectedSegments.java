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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair;

import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.sql.match.SQLConnectedSegmentsMatch;

import java.util.Collection;

import javax.xml.soap.Node;

public class SQLTransformationRepairConnectedSegments extends SQLTransformationRepair<SQLConnectedSegmentsMatch> {

	public SQLTransformationRepairConnectedSegments(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<SQLConnectedSegmentsMatch> matches) {
		for (final SQLConnectedSegmentsMatch csm : matches) {
			// delete segment2 with all its relationships
			final Node segment2 = csm.getSegment2();
			for (final Relationship relationship : segment2.getRelationships()) {
				relationship.delete();
			}
			segment2.delete();
			// (segment1)-[:connectsTo]->(segment3)
			csm.getSegment1().createRelationshipTo(csm.getSegment3(), relationshipTypeConnectsTo);
		}
	}

}
