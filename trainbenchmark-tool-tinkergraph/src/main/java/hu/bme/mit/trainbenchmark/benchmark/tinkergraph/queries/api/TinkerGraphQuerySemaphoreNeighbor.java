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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api;

import java.util.ArrayList;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuery;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.util.TinkerGraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class TinkerGraphQuerySemaphoreNeighbor<TTinkerGraphDriver extends GraphDriver> extends TinkerGraphQuery<TinkerGraphSemaphoreNeighborMatch, TTinkerGraphDriver> {

	public TinkerGraphQuerySemaphoreNeighbor(final TTinkerGraphDriver driver) {
		super(RailwayQuery.SEMAPHORENEIGHBOR, driver);
	}

	@Override
	public Collection<TinkerGraphSemaphoreNeighborMatch> evaluate() {
		final Collection<TinkerGraphSemaphoreNeighborMatch> matches = new ArrayList<>();

		final Collection<Vertex> route1s = driver.getVertices(ModelConstants.ROUTE);

		for (final Vertex route1 : route1s) {
			// (route1:Route)-[:exit]->(semaphore:Semaphore)
			final Iterable<Vertex> semaphores = TinkerGraphUtil.getAdjacentNodes(route1, ModelConstants.EXIT, Direction.OUT,
					ModelConstants.SEMAPHORE);
			for (final Vertex semaphore : semaphores) {
				// (route1:Route)-[:requires]->(sensor1:Sensor)
				final Iterable<Vertex> sensor1s = TinkerGraphUtil.getAdjacentNodes(route1, ModelConstants.REQUIRES, Direction.OUT,
						ModelConstants.SENSOR);
				for (final Vertex sensor1 : sensor1s) {
					// (sensor1:Sensor)<-[:sensor]-(te1:TrackElement)
					final Iterable<Vertex> te1s = TinkerGraphUtil.getAdjacentNodes(sensor1, ModelConstants.MONITORED_BY, Direction.IN,
							new String[] { ModelConstants.SEGMENT, ModelConstants.SWITCH });
					for (final Vertex te1 : te1s) {
						// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
						final Iterable<Vertex> te2s = TinkerGraphUtil.getAdjacentNodes(te1, ModelConstants.CONNECTS_TO, Direction.OUT,
								new String[] { ModelConstants.SEGMENT, ModelConstants.SWITCH });
						for (final Vertex te2 : te2s) {
							// (te2:TrackElement)-[:sensor]->(sensor2:Sensor)
							final Iterable<Vertex> sensor2s = TinkerGraphUtil.getAdjacentNodes(te2, ModelConstants.MONITORED_BY, Direction.OUT, ModelConstants.SENSOR);
							for (final Vertex sensor2 : sensor2s) {
								// (sensor2:Sensor)<-[:requires]-(route2:Route),
								final Iterable<Vertex> route2s = TinkerGraphUtil.getAdjacentNodes(sensor2, ModelConstants.REQUIRES, Direction.IN, ModelConstants.ROUTE);
								for (final Vertex route2 : route2s) {
									// route1 != route2 --> if (route1 == route2), continue
									if (route1.equals(route2)) {
										continue;
									}

									// (route2)-[:entry]->(semaphore) NAC
									if (!TinkerGraphUtil.isConnected(route2, semaphore, ModelConstants.ENTRY)) {
										matches.add(new TinkerGraphSemaphoreNeighborMatch(semaphore, route1, route2, sensor1, sensor2, te1, te2));
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
