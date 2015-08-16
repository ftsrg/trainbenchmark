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

package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics;

import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.util.Map.Entry;

public class DegreeDistribution extends Metric {

	public DegreeDistribution(String identifier, EdgeDirection direction) {
		super(identifier, direction);
	}

	public DegreeDistribution(Entry<Integer, Double> entry) {
		super(entry.getKey().toString(), EdgeDirection.BOTH);
		metricValue = (Double) entry.getValue();

	}

	@Override
	public void calculate() {

	}

}
