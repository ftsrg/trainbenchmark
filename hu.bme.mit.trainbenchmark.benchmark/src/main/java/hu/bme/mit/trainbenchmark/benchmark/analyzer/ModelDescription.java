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

package hu.bme.mit.trainbenchmark.benchmark.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.DegreeDistribution;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.Metric;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ModelDescription<D extends Driver<?>> extends Analyzer<D> {

	/**
	 * The first key is the type of the objects. Every one of the them contains another Map that includes
	 * the degrees of the nodes and their distributions.
	 */
	protected Map<String, Map<Integer, Double>> degreeDistributions;

	protected String ALL = "All";

	public ModelDescription(D driver) {
		super(driver);
	}

	@Override
	public void initializeMetrics() {
		degreeDistributions = new HashMap<>();
		if (metrics == null) {
			metrics = new ArrayList<Metric>();
		}

	}

	@Override
	public void resetMetrics() {
		degreeDistributions.clear();

	}

	@Override
	public void calculateAll() {
		calculateMetrics();
		for (String type : degreeDistributions.keySet()) {
			for (Entry<Integer, Double> entry : degreeDistributions.get(type).entrySet()) {
				metrics.add(new DegreeDistribution(entry));
			}
		}
	}

	protected void addDegree(String type, int degree) {
		if (!degreeDistributions.containsKey(type)) {
			degreeDistributions.put(type, new HashMap<Integer, Double>());
		}
		Map<Integer, Double> dists = degreeDistributions.get(type);
		double count;

		if (dists.containsKey(degree)) {
			count = dists.get(degree);
			count++;
			dists.put(degree, count);
		} else {
			dists.put(degree, 1.0);
		}

		if (!type.equals(ALL)) {
			addDegree(ALL, degree);
		}

	}
}
