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

package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries;

import java.util.ArrayList;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class TinkerGraphQuerySwitchMonitoredInject<TTinkerGraphDriver extends TinkerGraphDriver>
		extends TinkerGraphQuery<TinkerGraphSwitchMonitoredInjectMatch, TTinkerGraphDriver> {

	public TinkerGraphQuerySwitchMonitoredInject(final TTinkerGraphDriver driver) {
		super(RailwayQuery.SWITCHMONITORED, driver);
	}

	@Override
	public Collection<TinkerGraphSwitchMonitoredInjectMatch> evaluate() {
		final Collection<TinkerGraphSwitchMonitoredInjectMatch> matches = new ArrayList<>();

		return matches;
	}
}
