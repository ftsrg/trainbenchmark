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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core;

import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver.TYPE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerHelper;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.TinkerGraphChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphConnectedSegmentsMatch;

public class TinkerGraphConnectedSegmentsChecker extends TinkerGraphChecker<TinkerGraphConnectedSegmentsMatch> {

	public TinkerGraphConnectedSegmentsChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphConnectedSegmentsMatch> check() throws IOException {
		final Collection<TinkerGraphConnectedSegmentsMatch> matches = new ArrayList<>();

		final TinkerGraph graph = driver.getGraph();		
		final List<? extends Vertex> sensors = TinkerHelper.queryVertexIndex(graph, TYPE, SENSOR);
		
//		final GraphDatabaseService graphDb = driver.getGraphDb();
//		try (Transaction tx = graphDb.beginTx()) {
//
//			// (sensor:Sensor)
//			final ResourceIterator<Node> sensors = graphDb.findNodes(Neo4jConstants.labelSensor);
//			while (sensors.hasNext()) {
//				final Node sensor = sensors.next();
//				
//				// (sensor:Sensor)<-[:sensor]-(segment1:Segment)
//				final Iterator<Relationship> sensorEdges1 = sensor.getRelationships(Direction.INCOMING,
//						Neo4jConstants.relationshipTypeMonitoredBy).iterator();			
//				while (sensorEdges1.hasNext()) {
//					final Node segment1 = sensorEdges1.next().getStartNode();
//					if (!segment1.hasLabel(Neo4jConstants.labelSegment)) {
//						continue;
//					}
//					
//					// (segment1:Segment)-[:connectsTo]->(segment2:Segment)
//					final Iterator<Relationship> connectsTo1s = segment1.getRelationships(Direction.OUTGOING,
//							Neo4jConstants.relationshipTypeConnectsTo).iterator();
//					if (!connectsTo1s.hasNext()) {
//						continue;
//					}
//
//					final Node segment2 = connectsTo1s.next().getEndNode();
//					if (!segment2.hasLabel(Neo4jConstants.labelSegment)) {
//						continue;
//					}
//
//					// (segment2:Segment)-[:sensor]->(sensor:Sensor)
//					if (!isConnected(segment2, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
//						continue;
//					}
//					
//					// (segment2:Segment)-[:connectsTo]->(segment3:Segment)
//					final Iterator<Relationship> connectsTo2s = segment2.getRelationships(Direction.OUTGOING,
//							Neo4jConstants.relationshipTypeConnectsTo).iterator();
//					if (!connectsTo2s.hasNext()) {
//						continue;
//					}
//
//					final Node segment3 = connectsTo2s.next().getEndNode();
//					if (!segment3.hasLabel(Neo4jConstants.labelSegment)) {
//						continue;
//					}
//					
//					// (segment3:Segment)-[:sensor]->(sensor:Sensor)
//					if (!isConnected(segment3, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
//						continue;
//					}
//
//					// (segment3:Segment)-[:connectsTo]->(segment4:Segment)
//					final Iterator<Relationship> connectsTo3s = segment3.getRelationships(Direction.OUTGOING,
//							Neo4jConstants.relationshipTypeConnectsTo).iterator();
//					if (!connectsTo3s.hasNext()) {
//						continue;
//					}
//
//					final Node segment4 = connectsTo3s.next().getEndNode();
//					if (!segment4.hasLabel(Neo4jConstants.labelSegment)) {
//						continue;
//					}
//					
//					// (segment4:Segment)-[:sensor]->(sensor:Sensor)
//					if (!isConnected(segment4, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
//						continue;
//					}				
//
//					// (segment4:Segment)-[:connectsTo]->(segment5:Segment)
//					final Iterator<Relationship> connectsTo4s = segment4.getRelationships(Direction.OUTGOING,
//							Neo4jConstants.relationshipTypeConnectsTo).iterator();
//					if (!connectsTo4s.hasNext()) {
//						continue;
//					}
//
//					final Node segment5 = connectsTo4s.next().getEndNode();
//					if (!segment5.hasLabel(Neo4jConstants.labelSegment)) {
//						continue;
//					}
//					
//					// (segment5:Segment)-[:sensor]->(sensor:Sensor)
//					if (!isConnected(segment5, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
//						continue;
//					}
//
//					// (segment5:Segment)-[:connectsTo]->(segment6:Segment)
//					final Iterator<Relationship> connectsTo5s = segment5.getRelationships(Direction.OUTGOING,
//							Neo4jConstants.relationshipTypeConnectsTo).iterator();
//					if (!connectsTo5s.hasNext()) {
//						continue;
//					}
//
//					final Node segment6 = connectsTo5s.next().getEndNode();
//					if (!segment6.hasLabel(Neo4jConstants.labelSegment)) {
//						continue;
//					}
//
//					// (segment6:Segment)-[:sensor]->(sensor:Sensor)
//					if (!isConnected(segment6, sensor, Neo4jConstants.relationshipTypeMonitoredBy)) {
//						continue;
//					}
//					
//					final Map<String, Object> match = new HashMap<>();
//					match.put(VAR_SENSOR, sensor);
//					match.put(VAR_SEGMENT1, segment1);
//					match.put(VAR_SEGMENT2, segment2);
//					match.put(VAR_SEGMENT3, segment3);
//					match.put(VAR_SEGMENT4, segment4);
//					match.put(VAR_SEGMENT5, segment5);
//					match.put(VAR_SEGMENT6, segment6);
//					final Neo4jConnectedSegmentsMatch csm = new Neo4jConnectedSegmentsMatch(match);
//					matches.add(csm);
//				}
//			}
//		}

		return matches;
	}


}
