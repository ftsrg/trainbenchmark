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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4JApiQueryRouteSensor extends Neo4jApiQuery<Neo4jRouteSensorMatch> {

	public Neo4JApiQueryRouteSensor(final Neo4jDriver driver) {
		super(RailwayQuery.ROUTESENSOR, driver);
	}

	@Override
	public Collection<Neo4jRouteSensorMatch> evaluate() {
		final Collection<Neo4jRouteSensorMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (final Transaction tx = graphDb.beginTx()) {
			final Iterable<Node> routes = () -> graphDb.findNodes(Neo4jConstants.labelRoute);
			for (final Node route : routes) {
				// (route:Route)-[:follows]->(swp:switchPosition)
				final Iterable<Node> swPs = Neo4jUtil.getAdjacentNodes(route, Neo4jConstants.relationshipTypeFollows, Direction.OUTGOING, Neo4jConstants.labelSwitchPosition);
				for (final Node swP : swPs) {
					// (swP:switchPosition)-[:target]->(sw:Switch)
					final Iterable<Node> sws = Neo4jUtil.getAdjacentNodes(swP, Neo4jConstants.relationshipTypeTarget, Direction.OUTGOING, Neo4jConstants.labelSwitch);
					for (final Node sw : sws) {
						// (sw:Switch)-[:sensor]->(sensor:Sensor)
						final Iterable<Node> sensors = Neo4jUtil.getAdjacentNodes(sw, Neo4jConstants.relationshipTypeMonitoredBy, Direction.OUTGOING, Neo4jConstants.labelSensor);
						for (final Node sensor : sensors) {
							// (sensor:Sensor)<-[:requires]-(route:Route) NAC
							if (!Neo4jUtil.isConnected(route, sensor, Neo4jConstants.relationshipTypeRequires)) {
								final Map<String, Object> match = new HashMap<>();
								match.put(VAR_ROUTE, route);
								match.put(VAR_SENSOR, sensor);
								match.put(VAR_SWP, swP);
								match.put(VAR_SW, sw);
								matches.add(new Neo4jRouteSensorMatch(match));
							}
						}
					}
				}
			}
		}

		return matches;
	}
}
