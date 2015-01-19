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

package hu.bme.mit.trainbenchmark.benchmark.pojo.drools.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.pojo.drools.ResultListener;
import hu.bme.mit.trainbenchmark.pojo.Segment;

import java.util.ArrayList;

public class PosLength extends DroolsPojoBenchmarkCase<Segment> {

	protected ResultListener<Segment> listener;

	@Override
	public String getName() {
		return "PosLength";
	}

	@Override
	protected int checkConstraints() {
		if (query == null) {
			listener = new ResultListener<Segment>("segment");
			query = ksession.openLiveQuery("PosLength check", new Object[] {}, listener);

		} else {
			// activate lazy PHREAK evaluation
			ksession.fireAllRules();
		}
		invalids = new ArrayList<Segment>(listener.getMatching());
		return invalids.size();
	}

}
