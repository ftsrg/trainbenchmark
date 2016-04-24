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

import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver.TYPE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerHelper;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.TinkerGraphChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchSensorMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class TinkerGraphSwitchSensorChecker extends TinkerGraphChecker<TinkerGraphSwitchSensorMatch> {

	public TinkerGraphSwitchSensorChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSwitchSensorMatch> check() {
		final Collection<TinkerGraphSwitchSensorMatch> matches = new ArrayList<>();

		final TinkerGraph graph = driver.getGraph();

		final List<? extends Vertex> switches = TinkerHelper.queryVertexIndex(graph, TYPE, SEGMENT);

		// (sw:Switch)
		for (final Vertex sw : switches) {
			final Iterator<Vertex> vertices = sw.vertices(Direction.OUT, ModelConstants.MONITORED_BY);

			boolean hasSensor = false;
			while (vertices.hasNext()) {
				final Vertex sensor = vertices.next();
				if (ModelConstants.SENSOR.equals(sensor.label())) {
					hasSensor = true;
					break;
				}
			}
			if (!hasSensor) {
				final Map<String, Object> match = new HashMap<>();
				match.put(VAR_SW, sw);
				matches.add(new TinkerGraphSwitchSensorMatch(match));
			}
		}

		return matches;
	}
}
