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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class RouteSensor extends Neo4jJavaBenchmarkCase {

	@Override
	public Collection<Node> checkJava() {
		results = new HashSet<>();

		try (Transaction tx = graphDb.beginTx()) {
			// (route:Route)-[:follows]->()
			final ResourceIterable<Node> routes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelRoute);
			for (final Node route : routes) {
				final Iterable<Relationship> followss = route.getRelationships(Direction.OUTGOING, relationshipTypeFollows);

				for (final Relationship follows : followss) {
					final Node switchPosition = follows.getEndNode();

					// (switchPosition:SwitchPosition)-[:switch]->()
					if (!switchPosition.hasLabel(labelSwitchPosition)) {
						continue;
					}
					final Iterable<Relationship> relationshipSwitches = switchPosition.getRelationships(Direction.OUTGOING,
							relationshipTypeSwitch);
					for (final Relationship relationshipSwitch : relationshipSwitches) {
						final Node sw = relationshipSwitch.getEndNode();

						// (switch:Switch)-[:sensor]->()
						if (!sw.hasLabel(labelSwitch)) {
							continue;
						}
						final Iterable<Relationship> relationshipSensors = sw.getRelationships(Direction.OUTGOING, relationshipTypeSensor);
						for (final Relationship relationshipSensor : relationshipSensors) {
							final Node sensor = relationshipSensor.getEndNode();

							if (results.contains(sensor)) {
								continue;
							}

							// (sensor:Sensor)<-[:definedBy]-(Route) NAC
							if (!sensor.hasLabel(labelSensor)) {
								continue;
							}
							final Iterable<Relationship> definedBys = sensor
									.getRelationships(Direction.INCOMING, relationshipTypeDefinedBy);

							final List<Node> routes2 = new ArrayList<>();
							for (final Relationship definedBy : definedBys) {
								final Node route2 = definedBy.getStartNode();
								if (!route2.hasLabel(labelRoute)) {
									continue;
								}
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
