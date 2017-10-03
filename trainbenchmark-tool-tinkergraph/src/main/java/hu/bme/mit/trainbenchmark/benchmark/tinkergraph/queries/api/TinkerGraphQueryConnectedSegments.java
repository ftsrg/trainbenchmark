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

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.util.TinkerGraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class TinkerGraphQueryConnectedSegments<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphQuery<TinkerGraphConnectedSegmentsMatch, TTinkerGraphDriver> {

	public TinkerGraphQueryConnectedSegments(final TTinkerGraphDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS, driver);
	}

	@Override
	public Collection<TinkerGraphConnectedSegmentsMatch> evaluate() throws IOException {
		final Collection<TinkerGraphConnectedSegmentsMatch> matches = new ArrayList<>();

		final Collection<Vertex> sensors = driver.getVertices(ModelConstants.SENSOR);
		for (final Vertex sensor : sensors) {
			// (sensor:Sensor)<-[:monitoredBy]-(segment1:Segment)
			final Iterable<Vertex> segment1s = TinkerGraphUtil.getAdjacentNodes(sensor, ModelConstants.MONITORED_BY, Direction.IN, ModelConstants.SEGMENT);
			for (final Vertex segment1 : segment1s) {
				// (segment1:Segment)-[:connectsTo]->(segment2:Segment)
				// (segment2:Segment)-[:monitoredBy]->(sensor:Sensor)
				final Iterable<Vertex> segment2s = TinkerGraphUtil.getAdjacentNodes(segment1, ModelConstants.CONNECTS_TO, Direction.OUT,
						ModelConstants.SEGMENT);
				for (final Vertex segment2 : segment2s) {
					if (!TinkerGraphUtil.isConnected(segment2, sensor, ModelConstants.MONITORED_BY)) {
						continue;
					}

					// (segment2:Segment)-[:connectsTo]->(segment3:Segment)
					// (segment3:Segment)-[:monitoredBy]->(sensor:Sensor)
					final Iterable<Vertex> segment3s = TinkerGraphUtil.getAdjacentNodes(segment2, ModelConstants.CONNECTS_TO, Direction.OUT,
							ModelConstants.SEGMENT);
					for (final Vertex segment3 : segment3s) {
						if (!TinkerGraphUtil.isConnected(segment3, sensor, ModelConstants.MONITORED_BY)) {
							continue;
						}

						// (segment3:Segment)-[:connectsTo]->(segment4:Segment)
						// (segment4:Segment)-[:monitoredBy]->(sensor:Sensor)
						final Iterable<Vertex> segment4s = TinkerGraphUtil.getAdjacentNodes(segment3, ModelConstants.CONNECTS_TO, Direction.OUT,
								ModelConstants.SEGMENT);
						for (final Vertex segment4 : segment4s) {
							if (!TinkerGraphUtil.isConnected(segment4, sensor, ModelConstants.MONITORED_BY)) {
								continue;
							}

							// (segment4:Segment)-[:connectsTo]->(segment5:Segment)
							// (segment5:Segment)-[:monitoredBy]->(sensor:Sensor)
							final Iterable<Vertex> segment5s = TinkerGraphUtil.getAdjacentNodes(segment4, ModelConstants.CONNECTS_TO, Direction.OUT,
									ModelConstants.SEGMENT);
							for (final Vertex segment5 : segment5s) {
								if (!TinkerGraphUtil.isConnected(segment5, sensor, ModelConstants.MONITORED_BY)) {
									continue;
								}

								// (segment5:Segment)-[:connectsTo]->(segment6:Segment)
								// (segment6:Segment)-[:monitoredBy]->(sensor:Sensor)
								final Iterable<Vertex> segment6s = TinkerGraphUtil.getAdjacentNodes(segment5, ModelConstants.CONNECTS_TO, Direction.OUT,
										ModelConstants.SEGMENT);
								for (final Vertex segment6 : segment6s) {
									if (!TinkerGraphUtil.isConnected(segment6, sensor, ModelConstants.MONITORED_BY)) {
										continue;
									}

									matches.add(new TinkerGraphConnectedSegmentsMatch(sensor, segment1, segment2, segment3, segment4, segment5, segment6));
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
