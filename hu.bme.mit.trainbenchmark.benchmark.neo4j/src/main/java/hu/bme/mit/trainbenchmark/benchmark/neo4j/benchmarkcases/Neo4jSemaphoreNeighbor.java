/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jSemaphoreNeighbor extends Neo4jJavaBenchmarkCase {

	@Override
	public Collection<Node> checkJava() {
		results = new HashSet<>();

		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterable<Node> routes1 = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelRoute);
			for (final Node route1 : routes1) {
				if (results.contains(route1)) {
					continue;
				}

				// (route1:Route)<-[:exit]->(semaphore:Semaphore)
				final Iterable<Relationship> exits = route1.getRelationships(Direction.OUTGOING, relationshipTypeExit);
				for (final Relationship exit : exits) {
					final Node semaphore = exit.getEndNode();
					if (!semaphore.hasLabel(labelSemaphore)) {
						continue;
					}

					// (route1:Route)-[:definedBy]->(sensor1:Sensor)
					final Iterable<Relationship> definedBys1 = route1.getRelationships(Direction.OUTGOING, relationshipTypeDefinedBy);
					for (final Relationship definedBy1 : definedBys1) {
						final Node sensor1 = definedBy1.getEndNode();

						// (sensor1:Sensor)<-[:sensor]-(te1:TrackElement)
						final Iterable<Relationship> relationshipSensors1 = sensor1.getRelationships(Direction.INCOMING,
								relationshipTypeSensor);
						for (final Relationship relationshipSensor : relationshipSensors1) {
							final Node te1 = relationshipSensor.getStartNode();
							if (!te1.hasLabel(labelTrackElement)) {
								continue;
							}

							// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
							final Iterable<Relationship> connectsTos = te1.getRelationships(Direction.OUTGOING, relationshipTypeConnectsTo);
							for (final Relationship connectsTo : connectsTos) {
								final Node te2 = connectsTo.getEndNode();
								if (!te2.hasLabel(labelTrackElement)) {
									continue;
								}

								// (te2:TrackElement)-[:sensor]->(sensor2:Sensor)
								final Iterable<Relationship> relationshipSensors2 = te2.getRelationships(Direction.OUTGOING,
										relationshipTypeSensor);
								for (final Relationship relationshipSensor2 : relationshipSensors2) {
									final Node sensor2 = relationshipSensor2.getEndNode();
									if (!sensor2.hasLabel(labelSensor)) {
										continue;
									}

									// (sensor2:Sensor)<-[:definedBy]-(route2:Route),
									final Iterable<Relationship> definedBys2 = sensor2.getRelationships(Direction.INCOMING,
											relationshipTypeDefinedBy);
									for (final Relationship definedBy2 : definedBys2) {
										final Node route2 = definedBy2.getStartNode();
										if (!route2.hasLabel(labelRoute)) {
											continue;
										}

										// route1 != route2 --> if (route1 == route2), break
										if (route1.getId() == route2.getId()) {
											break;
										}

										// (route2)-[:entry]-(semaphore) NAC
										final Iterable<Relationship> entries2 = route2.getRelationships(Direction.OUTGOING,
												relationshipTypeEntry);
										final Iterator<Relationship> entriesIterator2 = entries2.iterator();
										if (!entriesIterator2.hasNext()) {
											System.out.println("no entry");
											results.add(route1);
											break;
										}
									
										final Node entrySemaphore = entriesIterator2.next().getEndNode();
										if (!entrySemaphore.equals(semaphore)) {
											results.add(route1);
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
		System.out.println(results);

		return results;
	}

}
