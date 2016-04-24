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
import java.util.HashMap;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.TinkerGraphChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.util.TinkerGraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

public class TinkerGraphSemaphoreNeighborChecker extends TinkerGraphChecker<TinkerGraphSemaphoreNeighborMatch> {

	public TinkerGraphSemaphoreNeighborChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSemaphoreNeighborMatch> check() {
		final Collection<TinkerGraphSemaphoreNeighborMatch> matches = new ArrayList<>();

		final Collection<Vertex> route1s = driver.collectVertices(ModelConstants.ROUTE);

		for (final Vertex route1 : route1s) {
			// (route1:Route)-[:exit]->(semaphore:Semaphore)

			final Iterable<Vertex> semaphores = () -> route1.vertices(Direction.OUT, ModelConstants.EXIT);
			for (final Vertex semaphore : semaphores) {
				if (!semaphore.label().equals(ModelConstants.SEMAPHORE)) {
					continue;
				}
				
				// (route1:Route)-[:definedBy]->(sensor1:Sensor)
				final Iterable<Vertex> sensor1s = () -> route1.vertices(Direction.OUT, ModelConstants.GATHERS);
				for (final Vertex sensor1 : sensor1s) {
					// (sensor1:Sensor)<-[:sensor]-(te1:TrackElement)
					final Iterable<Vertex> te1s = () -> sensor1.vertices(Direction.IN, ModelConstants.MONITORED_BY);
					for (final Vertex te1 : te1s) {
						if (!(te1.label().equals(ModelConstants.SEGMENT) || !te1.label().equals(ModelConstants.SWITCH))) {
							continue;
						}
					
						// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
						final Iterable<Vertex> te2s = () -> te1.vertices(Direction.OUT, ModelConstants.CONNECTS_TO);
						for (final Vertex te2 : te2s) {							
							if (!(te2.label().equals(ModelConstants.SEGMENT) || !te2.label().equals(ModelConstants.SWITCH))) {
								continue;
							}

							// (te2:TrackElement)-[:sensor]->(sensor2:Sensor)
							final Iterable<Vertex> sensor2s = () -> te2.vertices(Direction.OUT, ModelConstants.MONITORED_BY);
							for (final Vertex sensor2 : sensor2s) {
								if (!sensor2.label().equals(ModelConstants.SENSOR)) {
									continue;
								}

								// (sensor2:Sensor)<-[:gathers]-(route2:Route),
								final Iterable<Vertex> route2s = () -> sensor2.vertices(Direction.IN, ModelConstants.GATHERS);
								for (final Vertex route2 : route2s) {
									if (!route2.label().equals(ModelConstants.ROUTE)) {
										continue;
									}

									// route1 != route2 --> if (route1 == route2), break
									if (route1.id() == route2.id()) {
										break;
									}

									// (route2)-[:entry]->(semaphore) NAC
									if (!TinkerGraphUtil.isConnected(route2, semaphore, ModelConstants.ENTRY)) {
										final Map<String, Object> match = new HashMap<>();
										match.put(QueryConstants.VAR_SEMAPHORE, semaphore);
										match.put(QueryConstants.VAR_ROUTE1, route1);
										match.put(QueryConstants.VAR_ROUTE2, route2);
										match.put(QueryConstants.VAR_SENSOR1, sensor1);
										match.put(QueryConstants.VAR_SENSOR2, sensor2);
										match.put(QueryConstants.VAR_TE1, te1);
										match.put(QueryConstants.VAR_TE2, te2);
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

		return matches;
	}
}
