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

package hu.bme.mit.trainbenchmark.benchmark.drools.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.drools.ResultListener;

import java.util.ArrayList;

import Concept.Route;

public class SignalNeighbor extends Drools5BenchmarkCase {

	protected ResultListener<Route> listener;

	@Override
	public String getName() {
		return "SignalNeighbor";
	}

	@Override
	protected int doCCheck() {
		if (query == null) {
			listener = new ResultListener<Route>("route");
			query = ksession.openLiveQuery("SignalNeighbor check", new Object[] {}, listener);

		}
		invalids = new ArrayList<Route>(listener.getMatching());
		return invalids.size();
	}

}
