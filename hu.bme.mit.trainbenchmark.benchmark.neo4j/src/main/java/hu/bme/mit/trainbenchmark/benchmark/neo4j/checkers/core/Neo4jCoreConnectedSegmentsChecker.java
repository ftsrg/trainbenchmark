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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.core;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil;

public class Neo4jCoreConnectedSegmentsChecker extends Neo4jCoreChecker<Neo4jConnectedSegmentsMatch> {

	public Neo4jCoreConnectedSegmentsChecker(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public Collection<Neo4jConnectedSegmentsMatch> check() throws IOException {
		final Collection<Neo4jConnectedSegmentsMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {

			// (sensor:Sensor)
			final ResourceIterator<Node> sensors = graphDb.findNodes(Neo4jConstants.labelSensor);
			while (sensors.hasNext()) {
				final Node sensor = sensors.next();

				// (sensor:Sensor)<-[:sensor]-(segment1:Segment)
				final Iterator<Relationship> sensorEdges1 = sensor
						.getRelationships(Direction.INCOMING, Neo4jConstants.relationshipTypeMonitoredBy).iterator();
				while (sensorEdges1.hasNext()) {
					final Node segment1 = sensorEdges1.next().getStartNode();
					if (!segment1.hasLabel(Neo4jConstants.labelSegment)) {
						continue;
					}

					// (segment1:Segment)-[:connectsTo]->(segment2:Segment)
					final Iterable<Relationship> connectsTo1s = segment1.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeConnectsTo);
					for (final Relationship connectsTo1 : connectsTo1s) {
						final Node segment2 = connectsTo1.getEndNode();
						// (segment2:Segment)-[:sensor]->(sensor:Sensor)
						if (!segment2.hasLabel(Neo4jConstants.labelSegment)
								|| !Neo4jUtil.isConnected(segment2, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
							continue;
						}

						// (segment2:Segment)-[:connectsTo]->(segment3:Segment)
						final Iterable<Relationship> connectsTo2s = segment2.getRelationships(Direction.OUTGOING,
								Neo4jConstants.relationshipTypeConnectsTo);
						for (final Relationship connectsTo2 : connectsTo2s) {
							// (segment3:Segment)-[:sensor]->(sensor:Sensor)
							final Node segment3 = connectsTo2.getEndNode();
							if (!segment3.hasLabel(Neo4jConstants.labelSegment)
									|| !Neo4jUtil.isConnected(segment3, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
								continue;
							}

							// (segment3:Segment)-[:connectsTo]->(segment4:Segment)
							final Iterable<Relationship> connectsTo3s = segment3.getRelationships(Direction.OUTGOING,
									Neo4jConstants.relationshipTypeConnectsTo);
							for (final Relationship connectsTo3 : connectsTo3s) {
								// (segment4:Segment)-[:sensor]->(sensor:Sensor)
								final Node segment4 = connectsTo3.getEndNode();
								if (!segment4.hasLabel(Neo4jConstants.labelSegment)
										|| !Neo4jUtil.isConnected(segment4, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
									continue;
								}

								// (segment4:Segment)-[:connectsTo]->(segment5:Segment)
								final Iterable<Relationship> connectsTo4s = segment4.getRelationships(Direction.OUTGOING,
										Neo4jConstants.relationshipTypeConnectsTo);
								for (final Relationship connectsTo4 : connectsTo4s) {
									// (segment5:Segment)-[:sensor]->(sensor:Sensor)
									final Node segment5 = connectsTo4.getEndNode();
									if (!segment5.hasLabel(Neo4jConstants.labelSegment)
											|| !Neo4jUtil.isConnected(segment5, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
										continue;
									}

									// (segment5:Segment)-[:connectsTo]->(segment6:Segment)
									final Iterable<Relationship> connectsTo5s = segment5.getRelationships(Direction.OUTGOING,
											Neo4jConstants.relationshipTypeConnectsTo);
									for (final Relationship connectsTo5 : connectsTo5s) {
										final Node segment6 = connectsTo5.getEndNode();
										if (!segment6.hasLabel(Neo4jConstants.labelSegment)) {
											continue;
										}

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
										final Neo4jConnectedSegmentsMatch csm = new Neo4jConnectedSegmentsMatch(match);
										matches.add(csm);
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
