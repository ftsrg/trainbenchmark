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
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

import eu.mondo.sam.core.BenchmarkEngine;
import eu.mondo.sam.core.metrics.ScalarMetric;
import eu.mondo.sam.core.metrics.TimeMetric;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.JsonSerializer;
import eu.mondo.sam.core.results.PhaseResult;
import eu.mondo.sam.core.results.ResultSerializer;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationLogic;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

public final class BenchmarkRunner<TMatch, TElement, TDriver extends Driver<TElement>, TBenchmarkConfig extends BenchmarkConfig, TChecker extends Checker<TMatch>> {

	protected Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
	protected final TBenchmarkConfig benchmarkConfig;
	protected final AbstractBenchmarkCase<TMatch, TElement, TDriver, TBenchmarkConfig, TChecker> benchmarkCase;

	protected Transformation<?> transformation;
	protected TransformationLogic<TMatch, TElement, ?> transformationLogic;
	protected TDriver driver;
	protected Checker<TMatch> checker;
	protected Collection<TMatch> matches;

	public BenchmarkRunner(final TBenchmarkConfig benchmarkConfig,
			final AbstractBenchmarkCase<TMatch, TElement, TDriver, TBenchmarkConfig, TChecker> benchmarkCase) {
		this.benchmarkConfig = benchmarkConfig;
		this.benchmarkCase = benchmarkCase;
	}

	public BenchmarkResult runBenchmark() throws Exception {
		@SuppressWarnings("rawtypes")
		final ScenarioLogic scenario = ScenarioFactory.getScenario(benchmarkConfig.getScenario());

		scenario.setBenchmarkConfig(benchmarkConfig);
		scenario.initializeDescriptor();

		final BenchmarkEngine engine = new BenchmarkEngine();
		final ResultSerializer serializer = new JsonSerializer();
		engine.getBenchmarkResult().addSerializer(serializer);
		final TrainBenchmarkDataToken token = new TrainBenchmarkDataToken();
		token.setBenchmarkRunner(this);

		driver = benchmarkCase.createDriver(benchmarkConfig);
		checker = benchmarkCase.createChecker(benchmarkConfig, driver);

		for (int i = 1; i <= benchmarkConfig.getRuns(); i++) {
			scenario.setRunIndex(i);
			engine.runBenchmark(scenario, token);
		}

		return engine.getBenchmarkResult();
	}

	// initialization methods

	public final void initializeTransformation() throws IOException {
		transformation = benchmarkCase.createTransformation(benchmarkConfig, driver);
		transformationLogic = (TransformationLogic<TMatch, TElement, ?>) TransformationLogic.newInstance(benchmarkConfig.getScenario(),
				getComparator());
		if (transformationLogic != null) {
			transformationLogic.initialize(benchmarkConfig, driver, random);
		}
		transformationLogic.setTransformation(transformation);
	}

	// phases

	public final void read(final PhaseResult phaseResult) throws Exception {
		final TimeMetric timer = new TimeMetric("Time");
		timer.startMeasure();
		driver.read(benchmarkConfig.getModelPathNameWithoutExtension());
		timer.stopMeasure();
		phaseResult.addMetrics(timer);
	}

	public final void check(final PhaseResult phaseResult) throws Exception {
		final TimeMetric timer = new TimeMetric("Time");
		final ScalarMetric results = new ScalarMetric("Matches");
		timer.startMeasure();
		matches = checker.check();
		timer.stopMeasure();
		results.setValue(matches.size());
		phaseResult.addMetrics(timer, results);
	}

	public final void destroy() throws Exception {
		if (checker != null) {
			checker.destroy();
		}
		if (driver != null) {
			driver.destroy();
		}
	}

	public final void transform(final PhaseResult phaseResult) throws Exception {
		transformationLogic.performTransformation(phaseResult, matches);
	}

	// getters

	protected final Comparator<?> getComparator() {
		switch (benchmarkConfig.getScenario()) {
		case BATCH:
		case INJECT:
			return driver.getElementComparator();
		case REPAIR:
			return benchmarkCase.createMatchComparator();
		default:
			throw new UnsupportedOperationException();
		}
	}

	public TDriver getDriver() {
		return driver;
	}

	public BenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public AbstractBenchmarkCase<TMatch, TElement, TDriver, TBenchmarkConfig, TChecker> getBenchmarkCase() {
		return benchmarkCase;
	}

}
