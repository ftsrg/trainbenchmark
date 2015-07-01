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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

public class Neo4jCoreConnectedSegmentsChecker extends Neo4jCoreChecker<Neo4jConnectedSegmentsMatch> {

	public Neo4jCoreConnectedSegmentsChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jConnectedSegmentsMatch> check() throws IOException {
		final Collection<Neo4jConnectedSegmentsMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {

			// (sensor:Sensor)<-[:sensor]-()
			final ResourceIterator<Node> sensors = graphDb.findNodes(Neo4jConstants.labelSensor);
			while (sensors.hasNext()) {
				final Node sensor = sensors.next();
				final Iterator<Relationship> sensorEdges1 = sensor.getRelationships(Direction.INCOMING,
						Neo4jConstants.relationshipTypeSensor).iterator();			
				while (sensorEdges1.hasNext()) {
					final Node te1 = sensorEdges1.next().getStartNode();
					
					if (!te1.hasLabel(Neo4jConstants.labelSegment)) {
						continue;
					}
					
					// te1 is a Segment
					final Node segment1 = te1;
					
					// (segment1:Segment)-[:connectsTo]->()

					final Iterator<Relationship> connectsTo1s = segment1.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeConnectsTo).iterator();
					if (!connectsTo1s.hasNext()) {
						continue;
					}

					// (segment2:Segment)-[:connectsTo]->()
					final Node segment2 = connectsTo1s.next().getEndNode();
					final Iterator<Relationship> connectsTo2s = segment2.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeConnectsTo).iterator();
					if (!connectsTo2s.hasNext()) {
						continue;
					}

					// (segment3:Segment)-[:connectsTo]->()
					final Node segment3 = connectsTo2s.next().getEndNode();
					final Iterator<Relationship> connectsTo3s = segment3.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeConnectsTo).iterator();
					if (!connectsTo3s.hasNext()) {
						continue;
					}

					// (segment4:Segment)-[:connectsTo]->()
					final Node segment4 = connectsTo3s.next().getEndNode();
					final Iterator<Relationship> connectsTo4s = segment4.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeConnectsTo).iterator();
					if (!connectsTo4s.hasNext()) {
						continue;
					}

					// (segment5:Segment)-[:connectsTo]->()
					final Node segment5 = connectsTo4s.next().getEndNode();
					final Iterator<Relationship> connectsTo5s = segment5.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeConnectsTo).iterator();
					if (!connectsTo5s.hasNext()) {
						continue;
					}

					// (segment6:Segment)-[:connectsTo]->()
					final Node segment6 = connectsTo5s.next().getEndNode();
					final Iterator<Relationship> sensorEdges2 = segment6.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeSensor).iterator();
					if (!sensorEdges2.hasNext()) {
						continue;
					}

					final Node sensor2 = sensorEdges2.next().getEndNode();
					if (sensor.equals(sensor2)) {
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

		return matches;
	}

}
