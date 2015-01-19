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

import Concept.Switch;

public class SwitchSensor extends Drools6BenchmarkCase<Switch> {

	@Override
	public String getName() {
		return "SwitchSensor";
	}

	@Override
	protected int doCCheck() {
		if (query == null) {
			listener = new ResultListener<Switch>("switch");
			query = ksession.openLiveQuery("SwitchSensor check", new Object[] {}, listener);
		} else {
			// activate lazy PHREAK evaluation
			ksession.fireAllRules();
		}

		invalids = new ArrayList<Switch>(listener.getMatching());
		return invalids.size();
	}

}
