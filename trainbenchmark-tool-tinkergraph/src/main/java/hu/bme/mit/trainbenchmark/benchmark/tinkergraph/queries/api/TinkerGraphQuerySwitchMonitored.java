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
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.ArrayList;
import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;

public class TinkerGraphQuerySwitchMonitored<TTinkerGraphDriver extends GraphDriver> extends TinkerGraphQuery<TinkerGraphSwitchMonitoredMatch, TTinkerGraphDriver> {

	public TinkerGraphQuerySwitchMonitored(final TTinkerGraphDriver driver) {
		super(RailwayQuery.SWITCHMONITORED, driver);
	}

	@Override
	public Collection<TinkerGraphSwitchMonitoredMatch> evaluate() {
		final Collection<TinkerGraphSwitchMonitoredMatch> matches = new ArrayList<>();

		final Collection<Vertex> switches = driver.getVertices(SWITCH);

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
				matches.add(new TinkerGraphSwitchMonitoredMatch(sw));
			}
		}

		return matches;
	}
}
