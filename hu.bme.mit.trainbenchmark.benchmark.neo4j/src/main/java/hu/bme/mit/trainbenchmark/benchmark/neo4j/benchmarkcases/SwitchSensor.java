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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_SENSOR;

import java.util.ArrayList;
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

public class SwitchSensor extends Neo4jBenchmarkCase {

	@Override
	public String getName() {
		return "SwitchSensor";
	}

	@Override
	public List<Node> checkJava() {
		final Label labelSwitch = DynamicLabel.label(SWITCH);
		final Label labelSensor = DynamicLabel.label(SENSOR);

		final DynamicRelationshipType trackElement_sensorRelationshipType = DynamicRelationshipType.withName(TRACKELEMENT_SENSOR);

		results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			// (switch:Switch)-[TRACKELEMENT_SENSOR]->(Sensor) NAC
			final ResourceIterable<Node> switches = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelSwitch);
			for (final Node aSwitch : switches) {

				final Iterable<Relationship> trackElement_sensors = aSwitch.getRelationships(Direction.OUTGOING,
						trackElement_sensorRelationshipType);

				boolean hasSensor = false;
				for (final Relationship trackElement_sensor : trackElement_sensors) {
					final Node sensor = trackElement_sensor.getEndNode();
					if (sensor.hasLabel(labelSensor)) {
						hasSensor = true;
						break;
					}
				}

				if (!hasSensor) {
					results.add(aSwitch);
				}
			}
		}

		return results;
	}

}
