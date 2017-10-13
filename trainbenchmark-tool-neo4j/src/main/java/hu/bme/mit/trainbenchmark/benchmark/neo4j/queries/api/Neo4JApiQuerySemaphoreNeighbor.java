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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4JApiQuerySemaphoreNeighbor extends Neo4jApiQuery<Neo4jSemaphoreNeighborMatch> {

	public Neo4JApiQuerySemaphoreNeighbor(final Neo4jDriver driver) {
		super(RailwayQuery.SEMAPHORENEIGHBOR, driver);
	}

	@Override
	public Collection<Neo4jSemaphoreNeighborMatch> evaluate() {
		final Collection<Neo4jSemaphoreNeighborMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (final Transaction tx = graphDb.beginTx()) {
			final Iterable<Node> route1s = () -> graphDb.findNodes(Neo4jConstants.labelRoute);
			for (final Node route1 : route1s) {
				// (route1:Route)-[:exit]->(semaphore:Semaphore)
				final Iterable<Node> semaphores = Neo4jUtil.getAdjacentNodes(route1, Neo4jConstants.relationshipTypeExit,
						Direction.OUTGOING, Neo4jConstants.labelSemaphore);
				for (final Node semaphore : semaphores) {

					// (route1:Route)-[:requires]->(sensor1:Sensor)
					final Iterable<Node> sensor1s = Neo4jUtil.getAdjacentNodes(route1, Neo4jConstants.relationshipTypeRequires,
							Direction.OUTGOING, Neo4jConstants.labelSensor);
					for (final Node sensor1 : sensor1s) {
						// (sensor1:Sensor)<-[:sensor]-(te1:TrackElement)
						final Iterable<Node> te1s = Neo4jUtil.getAdjacentNodes(sensor1, Neo4jConstants.relationshipTypeMonitoredBy, Direction.INCOMING, Neo4jConstants.labelTrackElement);
						for (final Node te1 : te1s) {
							// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
							final Iterable<Node> te2s = Neo4jUtil.getAdjacentNodes(te1, Neo4jConstants.relationshipTypeConnectsTo, Direction.OUTGOING, Neo4jConstants.labelTrackElement);
							for (final Node te2 : te2s) {
								// (te2:TrackElement)-[:sensor]->(sensor2:Sensor)
								final Iterable<Node> sensor2s = Neo4jUtil.getAdjacentNodes(te2, Neo4jConstants.relationshipTypeMonitoredBy, Direction.OUTGOING, Neo4jConstants.labelSensor);
								for (final Node sensor2 : sensor2s) {
									// (sensor2:Sensor)<-[:requires]-(route2:Route),
									final Iterable<Node> route2s = Neo4jUtil.getAdjacentNodes(sensor2, Neo4jConstants.relationshipTypeRequires, Direction.INCOMING, Neo4jConstants.labelRoute);
									for (final Node route2 : route2s) {
										// route1 != route2 --> if (route1 == route2), continue
										if (route1.equals(route2)) {
											continue;
										}

										// (route2)-[:entry]->(semaphore) NAC
										if (!Neo4jUtil.isConnected(route2, semaphore, Neo4jConstants.relationshipTypeEntry)) {
											final Map<String, Object> match = new HashMap<>();
											match.put(VAR_SEMAPHORE, semaphore);
											match.put(VAR_ROUTE1, route1);
											match.put(VAR_ROUTE2, route2);
											match.put(VAR_SENSOR1, sensor1);
											match.put(VAR_SENSOR2, sensor2);
											match.put(VAR_TE1, te1);
											match.put(VAR_TE2, te2);
											matches.add(new Neo4jSemaphoreNeighborMatch(match));
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return matches;
	}
}
