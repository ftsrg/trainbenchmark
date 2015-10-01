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

package hu.bme.mit.trainbenchmark.benchmark.token;

import eu.mondo.sam.core.DataToken;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

public class TrainBenchmarkDataToken implements DataToken {

	protected AbstractBenchmarkCase<?, ?> benchmarkCase;
	protected BenchmarkConfig config;

	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}

	public AbstractBenchmarkCase<?, ?> getBenchmarkCase() {
		return benchmarkCase;
	}

	public void setBenchmarkCase(final AbstractBenchmarkCase<?, ?> benchmarkCase) {
		this.benchmarkCase = benchmarkCase;
	}

	public BenchmarkConfig getConfig() {
		return config;
	}

	public void setConfig(final BenchmarkConfig config) {
		this.config = config;
	}

}
