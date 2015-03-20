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

package hu.bme.mit.trainbenchmark.benchmark.drools6.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.drools6.ResultListener;
import hu.bme.mit.trainbenchmark.railway.Route;

import java.util.ArrayList;
import java.util.Collection;

public class SemaphoreNeighbor extends Drools6BenchmarkCase<Route> {

	@Override
	protected Collection<Route> check() {
		if (query == null) {
			listener = new ResultListener<Route>("route");
			query = ksession.openLiveQuery("SemaphoreNeighbor check", new Object[] {}, listener);
		} else {
			// activate lazy PHREAK evaluation
			ksession.fireAllRules();
		}
		results = new ArrayList<>(listener.getMatching());
		return results;
	}

}
