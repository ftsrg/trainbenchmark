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

import java.util.Collection;
import java.util.List;
import java.util.Random;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

public final class BenchmarkRunner<TMatch, TElement, TDriver extends Driver<TElement>, TBenchmarkConfig extends BenchmarkConfig, TChecker extends ModelQuery<TMatch, TDriver>> {

	protected final Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
	protected final TBenchmarkConfig benchmarkConfig;
	protected final AbstractBenchmarkCase<TMatch, TElement, TDriver, TBenchmarkConfig> benchmarkCase;

	protected ModelTransformation<?, ?> transformation;
	protected TDriver driver;
	protected List<TChecker> checkers;
	protected List<Collection<TMatch>> matches;

	public BenchmarkRunner(final TBenchmarkConfig benchmarkConfig,
			final AbstractBenchmarkCase<TMatch, TElement, TDriver, TBenchmarkConfig> benchmarkCase) {
		this.benchmarkConfig = benchmarkConfig;
		this.benchmarkCase = benchmarkCase;
	}

	public void runBenchmark() throws Exception {
//		@SuppressWarnings("rawtypes")
//		final TrainBenchmarkScenario scenario = ScenarioFactory.getScenario(benchmarkConfig.getScenario());
//
//		scenario.setBenchmarkConfig(benchmarkConfig);
//		scenario.initializeDescriptor();
//
//		BenchmarkResult result = null;
//		for (int i = 1; i <= benchmarkConfig.getRuns(); i++) {
//			final BenchmarkEngine engine = new BenchmarkEngine();
//
//			scenario.setRunIndex(i);
//			final FilenameFactory factory = new DefaultFilenameFactory(scenario.getCaseDescriptor());
//			result = new BenchmarkResult(new File("."));
//			final CsvPublisher publisher = new CsvPublisher(factory);
//			result.addPublisher(publisher);
//
//			final TrainBenchmarkDataToken token = new TrainBenchmarkDataToken();
//			token.setBenchmarkRunner(this);
//
//			driver = benchmarkCase.createDriver(benchmarkConfig);
//
//			// initialize checkers
//			checkers = new ArrayList<>();
//
//			for (final RailwayQuery query : benchmarkConfig.getQueries()) {
////				final TChecker checker = benchmarkCase.createModelQuery(benchmarkConfig, driver, query);
////				checkers.add(checker);
//			}
//
//			engine.runBenchmark(result, scenario, token);
//		}
//
//		return result;
	}

	// phases



//	public final void check(final PhaseResult phaseResult) throws Exception {

//	}

	public final void destroy() throws Exception {
//		for (final TChecker queries : checkers) {
//			queries.close();
//		}
		driver.destroy();
	}


	public TDriver getDriver() {
		return driver;
	}

	public BenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}


}
