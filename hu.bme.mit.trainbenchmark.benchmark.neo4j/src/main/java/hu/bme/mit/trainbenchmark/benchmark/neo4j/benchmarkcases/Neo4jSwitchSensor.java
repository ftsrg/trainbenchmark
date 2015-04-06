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

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jSwitchSensor extends Neo4jJavaBenchmarkCase {

	@Override
	public Collection<Node> checkJava() {
		results = new HashSet<>();

		try (Transaction tx = graphDb.beginTx()) {
			// (switch:Switch)-[:sensor]->(Sensor) NAC
			final ResourceIterable<Node> switches = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelSwitch);
			for (final Node sw : switches) {
				final Iterable<Relationship> relationshipSensors = sw.getRelationships(Direction.OUTGOING, relationshipTypeSensor);

				boolean hasSensor = false;
				for (final Relationship relationshipSensor : relationshipSensors) {
					final Node sensor = relationshipSensor.getEndNode();
					if (sensor.hasLabel(labelSensor)) {
						hasSensor = true;
						break;
					}
				}

				if (!hasSensor) {
					results.add(sw);
				}
			}
		}

		return results;
	}

}
