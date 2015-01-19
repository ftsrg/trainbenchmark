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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_ROUTEDEFINITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_SENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.SIGNALNEIGHBOR;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class SignalNeighbor extends Neo4jBenchmarkCase {

	@Override
	public String getName() {
		return SIGNALNEIGHBOR;
	}

	@Override
	public Set<Node> checkJava() {
		final Label labelRoute = DynamicLabel.label(ROUTE);
		final Label labelSensor = DynamicLabel.label(SENSOR);
		final Label labelSignal = DynamicLabel.label(SIGNAL);
		final Label labelTrackElement = DynamicLabel.label(TRACKELEMENT);

		final DynamicRelationshipType relationshipTypeRoute_entry = DynamicRelationshipType.withName(ROUTE_ENTRY.toUpperCase());
		final DynamicRelationshipType relationshipTypeRoute_exit = DynamicRelationshipType.withName(ROUTE_EXIT.toUpperCase());
		final DynamicRelationshipType relationshipTypeRoute_routeDefinition = DynamicRelationshipType.withName(ROUTE_ROUTEDEFINITION
				.toUpperCase());
		final DynamicRelationshipType relationshipTypeTrackElement_connectsTo = DynamicRelationshipType.withName(TRACKELEMENT_CONNECTSTO
				.toUpperCase());
		final DynamicRelationshipType relationshipTypeTrackElement_sensor = DynamicRelationshipType.withName(TRACKELEMENT_SENSOR
				.toUpperCase());

		invalids = new HashSet<>();

		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterable<Node> routes1 = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelRoute);
			for (final Node route1 : routes1) {
				if (invalids.contains(route1))
					continue;

				// (route1:Route)<-[ROUTE_EXIT]->(signal:Signal)
				final Iterable<Relationship> route_exitRelationships = route1.getRelationships(Direction.OUTGOING,
						relationshipTypeRoute_exit);
				for (final Relationship route_exit : route_exitRelationships) {
					final Node signal = route_exit.getEndNode();
					if (!signal.hasLabel(labelSignal))
						continue;

					// (route1:Route)-[ROUTE_ROUTEDEFINITION]->(sensor1:Sensor)
					final Iterable<Relationship> route_routeDefinitions = route1.getRelationships(Direction.OUTGOING,
							relationshipTypeRoute_routeDefinition);
					for (final Relationship route_routeDefinition1 : route_routeDefinitions) {
						final Node sensor1 = route_routeDefinition1.getEndNode();

						// (sensor1:Sensor)<-[TRACKELEMENT_SENSOR]-(te1:TrackElement)
						final Iterable<Relationship> trackElement_sensors1 = sensor1.getRelationships(Direction.INCOMING,
								relationshipTypeTrackElement_sensor);
						for (final Relationship trackElement_sensor1 : trackElement_sensors1) {
							final Node te1 = trackElement_sensor1.getStartNode();
							if (!te1.hasLabel(labelTrackElement))
								continue;

							// (te1:TrackElement)-[TRACKELEMENT_CONNECTSTO]->(te2:TrackElement)
							final Iterable<Relationship> trackElement_connectsTos = te1.getRelationships(Direction.OUTGOING,
									relationshipTypeTrackElement_connectsTo);
							for (final Relationship trackElement_connectsTo : trackElement_connectsTos) {
								final Node te2 = trackElement_connectsTo.getEndNode();
								if (!te2.hasLabel(labelTrackElement))
									continue;

								// (te2:TrackElement)-[TRACKELEMENT_SENSOR]->(sensor2:Sensor)
								final Iterable<Relationship> trackElement_sensors2 = te2.getRelationships(Direction.OUTGOING,
										relationshipTypeTrackElement_sensor);
								for (final Relationship trackElement_sensor2 : trackElement_sensors2) {
									final Node sensor2 = trackElement_sensor2.getEndNode();
									if (!sensor2.hasLabel(labelSensor))
										continue;

									final ResourceIterable<Node> routes2 = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(
											labelRoute);

									// (sensor2:Sensor)-[ROUTE_ROUTEDEFINITION]->(route3:Route),
									// route3 != route1
									final Iterable<Relationship> route_routeDefinitions3 = sensor2.getRelationships(Direction.INCOMING,
											relationshipTypeRoute_routeDefinition);
									boolean route3exists = false;
									for (final Relationship route_routeDefinition3 : route_routeDefinitions3) {
										final Node route3 = route_routeDefinition3.getStartNode();
										if (!route3.hasLabel(labelRoute))
											continue;
										if (route1.getId() != route3.getId()) {
											route3exists = true;
											break;
										}
									}
									if (!route3exists) {
										continue;
									}

									// (signal:Signal)<-[ROUTE_ENTRY]-(route2:Route)-[ROUTE_ROUTEDEFINITION]->(sensor2:Sensor)
									// NAC
									boolean hasRoute2 = false;
									for (final Node route2 : routes2) {
										final Collection<Object> signalsX = new ArrayList<>();
										final Iterable<Relationship> route_entries = route2.getRelationships(Direction.OUTGOING,
												relationshipTypeRoute_entry);
										for (final Relationship route_entry : route_entries) {
											final Node signalX = route_entry.getEndNode();
											signalsX.add(signalX);
										}

										final Collection<Object> sensorsX = new ArrayList<>();
										final Iterable<Relationship> route_routeDefinitionsX = route2.getRelationships(Direction.OUTGOING,
												relationshipTypeRoute_routeDefinition);
										for (final Relationship route_routeDefinitionX : route_routeDefinitionsX) {
											final Node sensorX = route_routeDefinitionX.getEndNode();
											sensorsX.add(sensorX);
										}

										if (signalsX.contains(signal) && sensorsX.contains(sensor2)) {
											hasRoute2 = true;
											break;
										}
									}

									if (!hasRoute2) {
										invalids.add(route1);
									}
								}
							}
						}
					}
				}
			}
		}

		return invalids;
	}

}
