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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.phases.analysis.MetricsCalculationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.analysis.MetricsInitializationPhase;
import hu.bme.mit.trainbenchmark.benchmark.phases.analysis.ModelMetricsCalculationPhase;
import eu.mondo.sam.core.results.CaseDescriptor;
import eu.mondo.sam.core.scenarios.BenchmarkScenario;

public abstract class Scenario<T extends AbstractBenchmarkCase<?, ?, ?>> extends BenchmarkScenario {

	protected BenchmarkConfig benchmarkConfig;
	protected MetricsCalculationPhase initMetrics;
	protected MetricsCalculationPhase calcMetrics;

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

	protected void createMetricsCalculationPhases(final boolean analyze) {
		initMetrics = new MetricsCalculationPhase();
		calcMetrics = new MetricsCalculationPhase();
		initMetrics.setAnalyze(analyze);
		calcMetrics.setAnalyze(analyze);
		initMetrics.setPhase(new MetricsInitializationPhase("InitMetrics"));
		calcMetrics.setPhase(new ModelMetricsCalculationPhase("CalcModelMetrics"));
	}

	@Override
	public CaseDescriptor getCaseDescriptor() {
		CaseDescriptor descriptor = new CaseDescriptor();
		descriptor.setCaseName(caseName);
		descriptor.setTool(tool);
		descriptor.setScenario(getName());
		descriptor.setSize(size);
		descriptor.setRunIndex(runIndex);
		return descriptor;
	}

	public abstract String getName();
}
