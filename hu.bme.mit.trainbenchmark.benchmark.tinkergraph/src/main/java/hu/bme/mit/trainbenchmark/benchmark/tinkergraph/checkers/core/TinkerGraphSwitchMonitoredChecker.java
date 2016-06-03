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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.TinkerGraphModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchMonitoredMatch;

public class TinkerGraphSwitchMonitoredChecker extends TinkerGraphModelQuery<TinkerGraphSwitchMonitoredMatch> {

	public TinkerGraphSwitchMonitoredChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSwitchMonitoredMatch> check() {
		final Collection<TinkerGraphSwitchMonitoredMatch> matches = new ArrayList<>();

		final Collection<Vertex> switches = driver.collectVertices(SWITCH);
		
		// (sw:Switch)
		for (final Vertex sw : switches) {
			final Iterable<Vertex> monitoredByVertices = () -> sw.vertices(Direction.OUT, MONITORED_BY);

			boolean hasSensor = false;
			for (final Vertex monitoredByVertex : monitoredByVertices) {
				if (monitoredByVertex.label().equals(SENSOR)) {
					hasSensor = true;
					break;
				}
			}

			if (!hasSensor) {
				final Map<String, Object> match = new HashMap<>();
				match.put(VAR_SW, sw);
				matches.add(new TinkerGraphSwitchMonitoredMatch(match));
			}
		}

		return matches;
	}
}
