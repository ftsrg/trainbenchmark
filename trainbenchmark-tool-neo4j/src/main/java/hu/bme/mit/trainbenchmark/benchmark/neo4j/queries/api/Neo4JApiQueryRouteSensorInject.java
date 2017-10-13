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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4JApiQueryRouteSensorInject extends Neo4jApiQuery<Neo4jRouteSensorInjectMatch> {

	public Neo4JApiQueryRouteSensorInject(final Neo4jDriver driver) {
		super(RailwayQuery.ROUTESENSOR_INJECT, driver);
	}

	@Override
	public Collection<Neo4jRouteSensorInjectMatch> evaluate() {
		final Collection<Neo4jRouteSensorInjectMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			// (route:Route)
			final Iterable<Node> routes = () -> graphDb.findNodes(Neo4jConstants.labelRoute);
			for (final Node route : routes) {

				final Iterable<Node> sensors = Neo4jUtil.getAdjacentNodes(route, Neo4jConstants.relationshipTypeRequires,
						Direction.OUTGOING, Neo4jConstants.labelSensor);

				for (final Node sensor : sensors) {
					final Map<String, Object> match = new HashMap<>();
					match.put(QueryConstants.VAR_ROUTE, route);
					match.put(QueryConstants.VAR_SENSOR, sensor);
					matches.add(new Neo4jRouteSensorInjectMatch(match));
				}
			}
		}

		return matches;
	}
}
