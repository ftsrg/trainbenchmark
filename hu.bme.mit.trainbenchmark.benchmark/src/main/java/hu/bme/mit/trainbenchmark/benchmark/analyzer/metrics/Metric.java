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

import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class Metric extends BenchmarkMetric {

	protected double metricValue;

	@Override
	public String getValue() {
		return Double.toString(metricValue);
	}

	public void initName() {
		metricName = getIdentifier();
	}

	protected abstract String getIdentifier();
}
