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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSegment;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeConnectsTo;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Collection;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class Neo4jTransformationUserConnectedSegments extends Neo4jTransformationUser {

	public Neo4jTransformationUserConnectedSegments(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public void rhs(final Collection<Node> segments) {
		for (final Node segment1 : segments) {
			final Iterable<Relationship> sensors = segment1.getRelationships(Direction.OUTGOING, Neo4jConstants.relationshipTypeSensor);
			if (!sensors.iterator().hasNext()) {
				continue;
			}

			final Node sensor = sensors.iterator().next().getEndNode();
			final Iterable<Relationship> segment3s = segment1.getRelationships(Direction.OUTGOING,
					Neo4jConstants.relationshipTypeConnectsTo);

			if (!segment3s.iterator().hasNext()) {
				continue;
			}
			final Relationship connectsTo = segment3s.iterator().next();
			final Node segment3 = connectsTo.getEndNode();

			// transformation
			connectsTo.delete();
			final Node segment2 = neoDriver.getGraphDb().createNode(labelSegment);
			segment1.createRelationshipTo(segment2, relationshipTypeConnectsTo);
			segment2.createRelationshipTo(segment3, relationshipTypeConnectsTo);
			segment2.createRelationshipTo(sensor, relationshipTypeSensor);
		}
	}

}
