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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core;

import java.util.ArrayList;
import java.util.Collection;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jActiveRouteMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Neo4jCoreQueryActiveRoute extends Neo4jCoreQuery<Neo4jActiveRouteMatch> {

	public Neo4jCoreQueryActiveRoute(final Neo4jDriver driver) {
		super(RailwayQuery.ACTIVEROUTE, driver);
	}

	@Override
	public Collection<Neo4jActiveRouteMatch> evaluate() {
		final Collection<Neo4jActiveRouteMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			
		}

		return matches;
	}
}
