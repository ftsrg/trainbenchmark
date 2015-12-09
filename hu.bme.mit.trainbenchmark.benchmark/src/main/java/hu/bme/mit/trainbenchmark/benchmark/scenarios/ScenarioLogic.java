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

import eu.mondo.sam.core.results.CaseDescriptor;
import eu.mondo.sam.core.scenarios.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

public abstract class ScenarioLogic<T extends AbstractBenchmarkCase<?, ?, ?, ?, ?>> extends BenchmarkScenario {

	protected BenchmarkConfig benchmarkConfig;

	public BenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public void setBenchmarkConfig(final BenchmarkConfig benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}

	public void initializeDescriptor() {
		caseName = benchmarkConfig.getCaseName();
		size = benchmarkConfig.getSize();
		tool = benchmarkConfig.getToolName();
	}

	@Override
	public CaseDescriptor getCaseDescriptor() {
		final CaseDescriptor descriptor = new CaseDescriptor.Builder()
				.case_(caseName) //
				.tool(tool) //
				.scenario(benchmarkConfig.getScenarioName()) //
				.artifact(Integer.toString(size)) //
				.run(runIndex) //
				.build();
		return descriptor;
	}

}
