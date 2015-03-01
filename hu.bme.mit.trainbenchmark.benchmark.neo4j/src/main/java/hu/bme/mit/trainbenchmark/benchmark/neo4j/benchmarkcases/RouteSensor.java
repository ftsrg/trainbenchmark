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
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_ROUTEDEFINITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION_SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_SENSOR;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class RouteSensor extends Neo4jBenchmarkCase {

	@Override
	public String getName() {
		return "RouteSensor";
	}

	@Override
	public Collection<Node> checkJava() {
		final Label labelRoute = DynamicLabel.label(ROUTE);
		final Label labelSwitchPosition = DynamicLabel.label(SWITCHPOSITION);
		final Label labelSwitch = DynamicLabel.label(SWITCH);
		final Label labelSensor = DynamicLabel.label(SENSOR);

		final DynamicRelationshipType relationshipTypeRoute_switchPosition = DynamicRelationshipType.withName(ROUTE_SWITCHPOSITION);
		final DynamicRelationshipType relationshipTypeSwitchPosition_switch = DynamicRelationshipType.withName(SWITCHPOSITION_SWITCH);
		final DynamicRelationshipType relationshipTypeTrackElement_sensor = DynamicRelationshipType.withName(TRACKELEMENT_SENSOR);
		final DynamicRelationshipType relationshipTypeRoute_routeDefinition = DynamicRelationshipType.withName(ROUTE_ROUTEDEFINITION);

		results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			// (route:Route)-[ROUTE_SWITCHPOSITION]->()
			final ResourceIterable<Node> routes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelRoute);
			for (final Node route : routes) {
				final Iterable<Relationship> route_switchPositions = route.getRelationships(Direction.OUTGOING,
						relationshipTypeRoute_switchPosition);

				for (final Relationship route_switchPosition : route_switchPositions) {
					final Node switchPosition = route_switchPosition.getEndNode();

					// (switchPosition:SwitchPosition)-[SWITCHPOSITION_SWITCH]->()
					if (!switchPosition.hasLabel(labelSwitchPosition))
						continue;
					final Iterable<Relationship> switchPosition_switches = switchPosition.getRelationships(Direction.OUTGOING,
							relationshipTypeSwitchPosition_switch);
					for (final Relationship switchPosition_switch : switchPosition_switches) {
						final Node aSwitch = switchPosition_switch.getEndNode();

						// (switch:Switch)-[TRACKELEMENT_SENSOR]->()
						if (!aSwitch.hasLabel(labelSwitch))
							continue;
						final Iterable<Relationship> trackElement_sensors = aSwitch.getRelationships(Direction.OUTGOING,
								relationshipTypeTrackElement_sensor);
						for (final Relationship trackElement_sensor : trackElement_sensors) {
							final Node sensor = trackElement_sensor.getEndNode();

							if (results.contains(sensor))
								continue;

							// (sensor:Sensor)<-[ROUTE_ROUTEDEFINITION]-(Route)
							// NAC
							if (!sensor.hasLabel(labelSensor))
								continue;
							final Iterable<Relationship> route_routeDefinitions = sensor.getRelationships(Direction.INCOMING,
									relationshipTypeRoute_routeDefinition);

							final List<Node> routes2 = new ArrayList<>();
							for (final Relationship route_routeDefinition : route_routeDefinitions) {
								final Node route2 = route_routeDefinition.getStartNode();
								if (!route2.hasLabel(labelRoute))
									continue;
								routes2.add(route2);
							}

							if (!routes2.contains(route)) {
								results.add(sensor);
							}
						}
					}
				}
			}
		}

		return results;
	}

}
