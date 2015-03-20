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
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_EDGE;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Signal;

import java.util.ArrayList;
import java.util.Collection;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class SwitchSet extends Neo4jBenchmarkCase {

	@Override
	public Collection<Node> checkJava() {
		final Label labelSwitch = DynamicLabel.label(SWITCH);
		final Label labelSwitchPosition = DynamicLabel.label(SWITCHPOSITION);
		final Label labelRoute = DynamicLabel.label(ROUTE);
		final Label labelSignal = DynamicLabel.label(SEMAPHORE);

		final DynamicRelationshipType relationshipTypeRoute_entry = DynamicRelationshipType.withName(ENTRY);
		final DynamicRelationshipType relationshipTypeRoute_switchPosition = DynamicRelationshipType.withName(FOLLOWS);
		final DynamicRelationshipType relationshipTypeSwitchPosition_switch = DynamicRelationshipType.withName(SWITCH_EDGE);

		results = new ArrayList<>();

		// MATCH
		// (signal:Signal)<-[:Route_entry]-(route:Route)-[:Route_switchPosition]->(sP:SwitchPosition)-[:SwitchPosition_switch]->(sw:Switch)
		// WHERE sw.Switch_currentState <> sP.SwitchPosition_switchState
		// RETURN DISTINCT sP

		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterable<Node> signals = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelSignal);
			for (final Node signal : signals) {
				// signal.Signal_currentState = "GO"
				final Object signal_currentState = signal.getProperty(SIGNAL);
				if (!Signal.GO.toString().equals(signal_currentState)) {
					continue;
				}

				// (signal:Signal)<-[:Route_entry]-(route:Route)
				final Iterable<Relationship> route_entries = signal.getRelationships(Direction.INCOMING, relationshipTypeRoute_entry);
				for (final Relationship route_entry : route_entries) {
					final Node route = route_entry.getStartNode();
					if (!route.hasLabel(labelRoute)) {
						continue;
					}

					// (route:Route)-[:Route_switchPosition]->(sP:SwitchPosition)
					final Iterable<Relationship> route_switchPositions = route.getRelationships(Direction.OUTGOING,
							relationshipTypeRoute_switchPosition);
					for (final Relationship route_switchPosition : route_switchPositions) {
						final Node sP = route_switchPosition.getEndNode();
						if (!sP.hasLabel(labelSwitchPosition)) {
							continue;
						}

						// (sP:SwitchPosition)-[:SwitchPosition_switch]->(sw:Switch)
						final Iterable<Relationship> switchPosition_switches = sP.getRelationships(Direction.OUTGOING,
								relationshipTypeSwitchPosition_switch);

						if (!switchPosition_switches.iterator().hasNext()) {
							continue;
						}

						final Node sw = switchPosition_switches.iterator().next().getEndNode();
						if (!sw.hasLabel(labelSwitch)) {
							continue;
						}

						final Object currentState = sw.getProperty(ModelConstants.CURRENTPOSITION);
						final Object switchState = sP.getProperty(ModelConstants.POSITION);

						if (!currentState.equals(switchState)) {
							results.add(sP);
						}
					}

					// (route:Route)-[:Route_switchPosition]->(sP:SwitchPosition)
					// route.getRelationships(Direction.OUTGOING);

				}

				// (sP:SwitchPosition)-[:SwitchPosition_switch]->(sw:Switch)

				// final Iterable<Relationship> trackElement_sensors = aSwitch.getRelationships(Direction.OUTGOING,
				// trackElement_sensorRelationshipType);
				//
				// boolean hasSensor = false;
				// for (final Relationship trackElement_sensor : trackElement_sensors) {
				// final Node sensor = trackElement_sensor.getEndNode();
				// if (sensor.hasLabel(labelSensor)) {
				// hasSensor = true;
				// break;
				// }
				// }
				//
				// if (!hasSensor) {
				// results.add(aSwitch);
				// }
			}
		}

		return results;
	}

}
