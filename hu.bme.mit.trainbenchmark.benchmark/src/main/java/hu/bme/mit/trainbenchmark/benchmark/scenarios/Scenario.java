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

package hu.bme.mit.trainbenchmark.benchmark.scenarios;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.publisher.TrainBenchmarkCaseDescriptor;
import eu.mondo.sam.core.scenarios.BenchmarkScenario;

public abstract class Scenario<T extends BenchmarkCase<?, ?, ?>> extends BenchmarkScenario {

	protected BenchmarkConfig benchmarkConfig;
	protected final TrainBenchmarkCaseDescriptor caseDescriptor = new TrainBenchmarkCaseDescriptor();
	protected BenchmarkCase<?, ?, Driver<T>> benchmarkCase;

	public BenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public void setBenchmarkConfig(BenchmarkConfig benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}

	public void initializeDescriptor() {
		caseName = benchmarkConfig.getQuery().toString();
		size = benchmarkConfig.getSize();
		tool = benchmarkConfig.getTool();
		runIndex = benchmarkConfig.getRunIndex();
	}

	@Override
	public TrainBenchmarkCaseDescriptor getCaseDescriptor() {
		caseDescriptor.setCaseName(caseName);
		caseDescriptor.setTool(tool);
		caseDescriptor.setScenario(getName());
		caseDescriptor.setSize(size);
		caseDescriptor.setRunIndex(runIndex);
		caseDescriptor.setModel(benchmarkConfig.getModelType().toString());
		return caseDescriptor;
	}

	public abstract String getName();

	public BenchmarkCase<?, ?, Driver<T>> getBenchmarkCase() {
		return benchmarkCase;
	}

	public void setBenchmarkCase(BenchmarkCase<?, ?, Driver<T>> benchmarkCase) {
		this.benchmarkCase = benchmarkCase;
	}

}
