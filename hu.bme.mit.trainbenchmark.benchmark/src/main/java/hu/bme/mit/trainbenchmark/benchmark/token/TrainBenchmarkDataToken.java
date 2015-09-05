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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.publisher.TrainBenchmarkCaseDescriptor;
import eu.mondo.sam.core.DataToken;

public class TrainBenchmarkDataToken implements DataToken {

	private BenchmarkCase<?, ?, ?> benchmarkCase;

	private BenchmarkConfig config;

	private TrainBenchmarkCaseDescriptor descriptor;

	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}

	public BenchmarkCase<?, ?, ?> getBenchmarkCase() {
		return benchmarkCase;
	}

	public void setBenchmarkCase(BenchmarkCase<?, ?, ?> benchmarkCase) {
		this.benchmarkCase = benchmarkCase;
	}

	public BenchmarkConfig getConfig() {
		return config;
	}

	public void setConfig(BenchmarkConfig config) {
		this.config = config;
	}

	public TrainBenchmarkCaseDescriptor getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(TrainBenchmarkCaseDescriptor descriptor) {
		this.descriptor = descriptor;
	}

}
