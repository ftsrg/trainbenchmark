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

package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.models;

import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BetweennessMetric extends ModelMetric {

	private Map<String, Integer> betweennessValues;

	public BetweennessMetric() {
		super(EdgeDirection.OUTGOING);
		betweennessValues = new HashMap<String, Integer>();
	}

	@Override
	public void calculate() {
		double pairs = analyzer.getShortestPathMetric().getPairs();
		metricValue = 0;
		for (Entry<String, Integer> entry : betweennessValues.entrySet()) {
			if (entry.getValue() > metricValue) {
				metricValue = entry.getValue();
			}
		}
		metricValue /= pairs;

	}

	@Override
	protected String getIdentifier() {
		return "Betweenness";
	}

	public void clear() {
		betweennessValues.clear();
	}

	public void addBetweenness(final String nodeID) {
		if (!betweennessValues.containsKey(nodeID)) {
			betweennessValues.put(nodeID, 1);
		} else {
			int value = betweennessValues.get(nodeID);
			value++;
			betweennessValues.put(nodeID, value);
		}
	}

}
