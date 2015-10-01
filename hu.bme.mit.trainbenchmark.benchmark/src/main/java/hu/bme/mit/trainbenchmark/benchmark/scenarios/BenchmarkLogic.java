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

import java.io.IOException;

import eu.mondo.sam.core.BenchmarkEngine;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.JsonSerializer;
import eu.mondo.sam.core.results.ResultSerializer;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCaseRunner;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;

public class BenchmarkLogic {

	protected final BenchmarkConfig bc;
	protected final AbstractBenchmarkCaseRunner benchmarkCaseRunner;

	public BenchmarkLogic(final BenchmarkConfig bc, final AbstractBenchmarkCaseRunner benchmarkCaseRunner) {
		this.bc = bc;
		this.benchmarkCaseRunner = benchmarkCaseRunner;
	}

	@SuppressWarnings("unchecked")
	public BenchmarkResult runBenchmark() throws IOException {
		@SuppressWarnings("rawtypes")
		final ScenarioLogic scenario = ScenarioFactory.getScenario(bc.getScenario());

		scenario.setBenchmarkConfig(bc);
		scenario.initializeDescriptor();

		final BenchmarkEngine engine = new BenchmarkEngine();
		final ResultSerializer serializer = new JsonSerializer();
		engine.getBenchmarkResult().addSerializer(serializer);
		final TrainBenchmarkDataToken token = new TrainBenchmarkDataToken();
		token.setBenchmarkCaseRunner(benchmarkCaseRunner);
		token.setBenchmarkConfig(bc);

		for (int i = 1; i <= bc.getRuns(); i++) {
			scenario.setRunIndex(i);
			engine.runBenchmark(scenario, token);
		}

		return engine.getBenchmarkResult();
	}

}
