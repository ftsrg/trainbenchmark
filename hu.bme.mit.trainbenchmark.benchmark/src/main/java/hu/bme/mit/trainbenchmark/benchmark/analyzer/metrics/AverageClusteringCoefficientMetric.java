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

import java.util.List;

public class AverageClusteringCoefficientMetric extends Metric {

	private String type;

	public AverageClusteringCoefficientMetric(String type) {
		super(EdgeDirection.OUTGOING);
		this.type = type;
	}

	public AverageClusteringCoefficientMetric() {
		super(EdgeDirection.OUTGOING);
		type = null;
	}

	@Override
	public void calculate() {
		List<Double> values;
		if (type == null) {
			values = analyzer.getClusteringCoefficients();
		} else {
			values = analyzer.getClusteringCoefficients(type);
		}

		double sumCoef = 0.0;
		for (Double coef : values) {
			sumCoef += coef;
		}
		metricValue = sumCoef / values.size();

	}

	@Override
	protected String getIdentifier() {
		if (type == null) {
			return "AvgClustering";
		} else {
			return "AvgClustering" + type;
		}
	}

}