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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.TinkerGraphChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborMatch;

public class TinkerGraphSemaphoreNeighborChecker extends TinkerGraphChecker<TinkerGraphSemaphoreNeighborMatch> {

	public TinkerGraphSemaphoreNeighborChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSemaphoreNeighborMatch> check() {
		final Collection<TinkerGraphSemaphoreNeighborMatch> matches = new ArrayList<>();

		final TinkerGraph graph = driver.getGraph();		
//		final List<? extends Vertex> routes = TinkerHelper.queryVertexIndex(graph, TYPE, ROUTE);

//		final GraphDatabaseService graphDb = driver.getGraphDb();
//		try (Transaction tx = graphDb.beginTx()) {
//			final ResourceIterator<Node> routes1 = graphDb.findNodes(Neo4jConstants.labelRoute);
//			while (routes1.hasNext()) {
//				final Node route1 = routes1.next();
//				if (matches.contains(route1)) {
//					continue;
//				}
//
//				// (route1:Route)-[:exit]->(semaphore:Semaphore)
//				final Iterable<Relationship> exits = route1.getRelationships(Direction.OUTGOING, Neo4jConstants.relationshipTypeExit);
//				for (final Relationship exit : exits) {
//					final Node semaphore = exit.getEndNode();
//					if (!semaphore.hasLabel(Neo4jConstants.labelSemaphore)) {
//						continue;
//					}
//
//					// (route1:Route)-[:definedBy]->(sensor1:Sensor)
//					final Iterable<Relationship> gatherss1 = route1.getRelationships(Direction.OUTGOING,
//							Neo4jConstants.relationshipTypeGathers);
//					for (final Relationship gathers1 : gatherss1) {
//						final Node sensor1 = gathers1.getEndNode();
//
//						// (sensor1:Sensor)<-[:sensor]-(te1:TrackElement)
//						final Iterable<Relationship> relationshipMonitoredBy1 = sensor1.getRelationships(Direction.INCOMING,
//								Neo4jConstants.relationshipTypeMonitoredBy);
//						for (final Relationship relationshipSensor : relationshipMonitoredBy1) {
//							final Node te1 = relationshipSensor.getStartNode();
//							if (!te1.hasLabel(Neo4jConstants.labelTrackElement)) {
//								continue;
//							}
//
//							// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
//							final Iterable<Relationship> connectsTos = te1.getRelationships(Direction.OUTGOING,
//									Neo4jConstants.relationshipTypeConnectsTo);
//							for (final Relationship connectsTo : connectsTos) {
//								final Node te2 = connectsTo.getEndNode();
//								if (!te2.hasLabel(Neo4jConstants.labelTrackElement)) {
//									continue;
//								}
//
//								// (te2:TrackElement)-[:sensor]->(sensor2:Sensor)
//								final Iterable<Relationship> relationshipMonitoredBy2 = te2.getRelationships(Direction.OUTGOING,
//										Neo4jConstants.relationshipTypeMonitoredBy);
//								for (final Relationship relationshipSensor2 : relationshipMonitoredBy2) {
//									final Node sensor2 = relationshipSensor2.getEndNode();
//									if (!sensor2.hasLabel(Neo4jConstants.labelSensor)) {
//										continue;
//									}
//
//									// (sensor2:Sensor)<-[:gathers]-(route2:Route),
//									final Iterable<Relationship> gatherss2 = sensor2.getRelationships(Direction.INCOMING,
//											Neo4jConstants.relationshipTypeGathers);
//									for (final Relationship gathers2 : gatherss2) {
//										final Node route2 = gathers2.getStartNode();
//										if (!route2.hasLabel(Neo4jConstants.labelRoute)) {
//											continue;
//										}
//
//										// route1 != route2 --> if (route1 == route2), break
//										if (route1.getId() == route2.getId()) {
//											break;
//										}
//
//										// (route2)-[:entry]-(semaphore) NAC
//										final Iterable<Relationship> entries2 = route2.getRelationships(Direction.OUTGOING,
//												Neo4jConstants.relationshipTypeEntry);
//										final Iterator<Relationship> entriesIterator2 = entries2.iterator();
//										if (!entriesIterator2.hasNext() || !entriesIterator2.next().getEndNode().equals(semaphore)) {
//											final Map<String, Object> match = new HashMap<>();
//											match.put(VAR_SEMAPHORE, semaphore);
//											match.put(VAR_ROUTE1, route1);
//											match.put(VAR_ROUTE2, route2);
//											match.put(VAR_SENSOR1, sensor1);
//											match.put(VAR_SENSOR2, sensor2);
//											match.put(VAR_TE1, te1);
//											match.put(VAR_TE2, te2);
//											matches.add(new Neo4jSemaphoreNeighborMatch(match));
//											break;
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}

		return matches;
	}
}
