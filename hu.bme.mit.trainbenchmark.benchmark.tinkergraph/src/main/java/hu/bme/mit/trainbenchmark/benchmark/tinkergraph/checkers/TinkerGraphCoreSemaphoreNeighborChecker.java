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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers;

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
import java.util.Iterator;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborMatch;

public class TinkerGraphCoreSemaphoreNeighborChecker extends TinkerGraphCoreChecker<TinkerGraphSemaphoreNeighborMatch> {

	public TinkerGraphCoreSemaphoreNeighborChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSemaphoreNeighborMatch> check() {
		final Collection<TinkerGraphSemaphoreNeighborMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterator<Node> routes1 = graphDb.findNodes(TinkerGraphConstants.labelRoute);
			while (routes1.hasNext()) {
				final Node route1 = routes1.next();
				if (matches.contains(route1)) {
					continue;
				}

				// (route1:Route)-[:exit]->(semaphore:Semaphore)
				final Iterable<Relationship> exits = route1.getRelationships(Direction.OUTGOING, TinkerGraphConstants.relationshipTypeExit);
				for (final Relationship exit : exits) {
					final Node semaphore = exit.getEndNode();
					if (!semaphore.hasLabel(TinkerGraphConstants.labelSemaphore)) {
						continue;
					}

					// (route1:Route)-[:definedBy]->(sensor1:Sensor)
					final Iterable<Relationship> gatherss1 = route1.getRelationships(Direction.OUTGOING,
							TinkerGraphConstants.relationshipTypeGathers);
					for (final Relationship gathers1 : gatherss1) {
						final Node sensor1 = gathers1.getEndNode();

						// (sensor1:Sensor)<-[:sensor]-(te1:TrackElement)
						final Iterable<Relationship> relationshipMonitoredBy1 = sensor1.getRelationships(Direction.INCOMING,
								TinkerGraphConstants.relationshipTypeMonitoredBy);
						for (final Relationship relationshipSensor : relationshipMonitoredBy1) {
							final Node te1 = relationshipSensor.getStartNode();
							if (!te1.hasLabel(TinkerGraphConstants.labelTrackElement)) {
								continue;
							}

							// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
							final Iterable<Relationship> connectsTos = te1.getRelationships(Direction.OUTGOING,
									TinkerGraphConstants.relationshipTypeConnectsTo);
							for (final Relationship connectsTo : connectsTos) {
								final Node te2 = connectsTo.getEndNode();
								if (!te2.hasLabel(TinkerGraphConstants.labelTrackElement)) {
									continue;
								}

								// (te2:TrackElement)-[:sensor]->(sensor2:Sensor)
								final Iterable<Relationship> relationshipMonitoredBy2 = te2.getRelationships(Direction.OUTGOING,
										TinkerGraphConstants.relationshipTypeMonitoredBy);
								for (final Relationship relationshipSensor2 : relationshipMonitoredBy2) {
									final Node sensor2 = relationshipSensor2.getEndNode();
									if (!sensor2.hasLabel(TinkerGraphConstants.labelSensor)) {
										continue;
									}

									// (sensor2:Sensor)<-[:gathers]-(route2:Route),
									final Iterable<Relationship> gatherss2 = sensor2.getRelationships(Direction.INCOMING,
											TinkerGraphConstants.relationshipTypeGathers);
									for (final Relationship gathers2 : gatherss2) {
										final Node route2 = gathers2.getStartNode();
										if (!route2.hasLabel(TinkerGraphConstants.labelRoute)) {
											continue;
										}

										// route1 != route2 --> if (route1 == route2), break
										if (route1.getId() == route2.getId()) {
											break;
										}

										// (route2)-[:entry]-(semaphore) NAC
										final Iterable<Relationship> entries2 = route2.getRelationships(Direction.OUTGOING,
												TinkerGraphConstants.relationshipTypeEntry);
										final Iterator<Relationship> entriesIterator2 = entries2.iterator();
										if (!entriesIterator2.hasNext() || !entriesIterator2.next().getEndNode().equals(semaphore)) {
											final Map<String, Object> match = new HashMap<>();
											match.put(VAR_SEMAPHORE, semaphore);
											match.put(VAR_ROUTE1, route1);
											match.put(VAR_ROUTE2, route2);
											match.put(VAR_SENSOR1, sensor1);
											match.put(VAR_SENSOR2, sensor2);
											match.put(VAR_TE1, te1);
											match.put(VAR_TE2, te2);
											matches.add(new TinkerGraphSemaphoreNeighborMatch(match));
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
