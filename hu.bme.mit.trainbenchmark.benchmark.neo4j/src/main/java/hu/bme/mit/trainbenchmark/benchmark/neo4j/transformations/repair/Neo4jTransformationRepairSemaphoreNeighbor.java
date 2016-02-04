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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeEntry;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;

import java.util.Collection;

import org.neo4j.graphdb.Node;

public class Neo4jTransformationRepairSemaphoreNeighbor extends Neo4jTransformationRepair<Neo4jSemaphoreNeighborMatch> {

	public Neo4jTransformationRepairSemaphoreNeighbor(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void performRHS(final Collection<Neo4jSemaphoreNeighborMatch> matches) {
		for (final Neo4jSemaphoreNeighborMatch snm : matches) {
			final Node semaphore = snm.getSemaphore();
			final Node route2 = snm.getRoute2();
			route2.createRelationshipTo(semaphore, relationshipTypeEntry);
		}
	}

}
