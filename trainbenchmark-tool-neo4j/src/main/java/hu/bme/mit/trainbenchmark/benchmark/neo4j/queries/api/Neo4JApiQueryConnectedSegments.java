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

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;

public class Neo4JApiQueryConnectedSegments extends Neo4jApiQuery<Neo4jConnectedSegmentsMatch> {

	public Neo4JApiQueryConnectedSegments(final Neo4jDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS, driver);
	}

	@Override
	public Collection<Neo4jConnectedSegmentsMatch> evaluate() throws IOException {
		final Collection<Neo4jConnectedSegmentsMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (final Transaction tx = graphDb.beginTx()) {

			// (sensor:Sensor)
			final Iterable<Node> sensors = () -> graphDb.findNodes(Neo4jConstants.labelSensor);
			for (final Node sensor : sensors) {
				// (sensor:Sensor)<-[:sensor]-(segment1:Segment)
				final Iterable<Node> segment1s = Neo4jUtil.getAdjacentNodes(sensor, Neo4jConstants.relationshipTypeMonitoredBy,
						Direction.INCOMING, Neo4jConstants.labelSegment);

				for (final Node segment1 : segment1s) {
					// (segment1:Segment)-[:connectsTo]->(segment2:Segment)
					final Iterable<Node> segment2s = Neo4jUtil.getAdjacentNodes(segment1, Neo4jConstants.relationshipTypeConnectsTo,
							Direction.OUTGOING, Neo4jConstants.labelSegment);
					for (final Node segment2 : segment2s) {
						// (segment2:Segment)-[:sensor]->(sensor:Sensor)
						if (!Neo4jUtil.isConnected(segment2, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
							continue;
						}
						// (segment2:Segment)-[:connectsTo]->(segment3:Segment)
						final Iterable<Node> segment3s = Neo4jUtil.getAdjacentNodes(segment2, Neo4jConstants.relationshipTypeConnectsTo,
								Direction.OUTGOING, Neo4jConstants.labelSegment);
						for (final Node segment3 : segment3s) {
							// (segment3:Segment)-[:sensor]->(sensor:Sensor)
							if (!Neo4jUtil.isConnected(segment3, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
								continue;
							}
							// (segment3:Segment)-[:connectsTo]->(segment4:Segment)
							final Iterable<Node> segment4s = Neo4jUtil.getAdjacentNodes(segment3, Neo4jConstants.relationshipTypeConnectsTo,
									Direction.OUTGOING, Neo4jConstants.labelSegment);
							for (final Node segment4 : segment4s) {
								// (segment4:Segment)-[:sensor]->(sensor:Sensor)
								if (!Neo4jUtil.isConnected(segment4, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
									continue;
								}

								// (segment4:Segment)-[:connectsTo]->(segment5:Segment)
								final Iterable<Node> segment5s = Neo4jUtil.getAdjacentNodes(segment4,
										Neo4jConstants.relationshipTypeConnectsTo, Direction.OUTGOING, Neo4jConstants.labelSegment);
								for (final Node segment5 : segment5s) {
									// (segment5:Segment)-[:sensor]->(sensor:Sensor)
									if (!Neo4jUtil.isConnected(segment5, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
										continue;
									}

									// (segment5:Segment)-[:connectsTo]->(segment6:Segment)
									final Iterable<Node> segment6s = Neo4jUtil.getAdjacentNodes(segment5, Neo4jConstants.relationshipTypeConnectsTo, Direction.OUTGOING, Neo4jConstants.labelSegment);
									for (final Node segment6 : segment6s) {
										// (segment6:Segment)-[:sensor]->(sensor:Sensor)
										if (!Neo4jUtil.isConnected(segment6, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
											continue;
										}

										final Map<String, Object> match = new HashMap<>();
										match.put(VAR_SENSOR, sensor);
										match.put(VAR_SEGMENT1, segment1);
										match.put(VAR_SEGMENT2, segment2);
										match.put(VAR_SEGMENT3, segment3);
										match.put(VAR_SEGMENT4, segment4);
										match.put(VAR_SEGMENT5, segment5);
										match.put(VAR_SEGMENT6, segment6);
										matches.add(new Neo4jConnectedSegmentsMatch(match));
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
