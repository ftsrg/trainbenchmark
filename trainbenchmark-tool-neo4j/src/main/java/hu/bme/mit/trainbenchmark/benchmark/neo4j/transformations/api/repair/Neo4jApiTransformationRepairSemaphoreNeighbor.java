/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;

import java.util.Collection;

public class Neo4jApiTransformationRepairSemaphoreNeighbor extends Neo4jApiTransformation<Neo4jSemaphoreNeighborMatch> {

	public Neo4jApiTransformationRepairSemaphoreNeighbor(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jSemaphoreNeighborMatch> matches) {
		for (final Neo4jSemaphoreNeighborMatch match : matches) {
			final Node semaphore = match.getSemaphore();
			final Node route2 = match.getRoute2();
			if (!route2.hasRelationship(Direction.OUTGOING, Neo4jConstants.relationshipTypeEntry)) {
				route2.createRelationshipTo(semaphore, Neo4jConstants.relationshipTypeEntry);
			}
		}
	}

}
