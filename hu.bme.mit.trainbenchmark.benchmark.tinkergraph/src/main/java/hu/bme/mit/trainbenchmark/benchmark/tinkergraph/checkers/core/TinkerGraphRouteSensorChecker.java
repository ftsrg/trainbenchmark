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
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphRouteSensorMatch;

public class TinkerGraphRouteSensorChecker extends TinkerGraphChecker<TinkerGraphRouteSensorMatch> {

	public TinkerGraphRouteSensorChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphRouteSensorMatch> check() {
		final Collection<TinkerGraphRouteSensorMatch> matches = new ArrayList<>();

		final TinkerGraph graph = driver.getGraph();		
//		final List<? extends Vertex> routes = TinkerHelper.queryVertexIndex(graph, TYPE, ROUTE);

		
//		final GraphDatabaseService graphDb = driver.getGraphDb();
//		try (Transaction tx = graphDb.beginTx()) {
//			// (route:Route)-[:follows]->()
//			final ResourceIterator<Node> routes = graphDb.findNodes(Neo4jConstants.labelRoute);
//			while (routes.hasNext()) {
//				final Node route = routes.next();
//				final Iterable<Relationship> followss = route.getRelationships(Direction.OUTGOING, Neo4jConstants.relationshipTypeFollows);
//
//				for (final Relationship follows : followss) {
//					final Node swP = follows.getEndNode();
//
//					// (swP:switchPosition)-[:switch]->()
//					if (!swP.hasLabel(Neo4jConstants.labelSwitchPosition)) {
//						continue;
//					}
//					final Iterable<Relationship> relationshipSwitches = swP.getRelationships(Direction.OUTGOING,
//							Neo4jConstants.relationshipTypeSwitch);
//					for (final Relationship relationshipSwitch : relationshipSwitches) {
//						final Node sw = relationshipSwitch.getEndNode();
//
//						// (switch:Switch)-[:sensor]->()
//						if (!sw.hasLabel(Neo4jConstants.labelSwitch)) {
//							continue;
//						}
//						final Iterable<Relationship> relationshipSensors = sw.getRelationships(Direction.OUTGOING,
//								Neo4jConstants.relationshipTypeMonitoredBy);
//						for (final Relationship relationshipSensor : relationshipSensors) {
//							final Node sensor = relationshipSensor.getEndNode();
//
//							if (matches.contains(sensor)) {
//								continue;
//							}
//
//							// (sensor:Sensor)<-[:definedBy]-(Route) NAC
//							if (!sensor.hasLabel(Neo4jConstants.labelSensor)) {
//								continue;
//							}
//							final Iterable<Relationship> definedBys = sensor.getRelationships(Direction.INCOMING,
//									Neo4jConstants.relationshipTypeGathers);
//
//							final List<Node> routes2 = new ArrayList<>();
//							for (final Relationship definedBy : definedBys) {
//								final Node route2 = definedBy.getStartNode();
//								if (!route2.hasLabel(Neo4jConstants.labelRoute)) {
//									continue;
//								}
//								routes2.add(route2);
//							}
//							
//							// TODO is there a :definedBy relationship from route to sensor
//							if (!routes2.contains(route)) {
//								final Map<String, Object> match = new HashMap<>();
//								match.put(VAR_ROUTE, route);
//								match.put(VAR_SENSOR, sensor);
//								match.put(VAR_SWP, swP);
//								match.put(VAR_SW, sw);
//								matches.add(new Neo4jRouteSensorMatch(match));
//							}
//						}
//					}
//				}
//			}
//		}

		return matches;
	}
}
