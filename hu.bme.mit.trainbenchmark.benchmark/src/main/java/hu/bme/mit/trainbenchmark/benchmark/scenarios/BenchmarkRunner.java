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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import eu.mondo.sam.core.BenchmarkEngine;
import eu.mondo.sam.core.metrics.ScalarMetric;
import eu.mondo.sam.core.metrics.TimeMetric;
import eu.mondo.sam.core.publishers.CsvPublisher;
import eu.mondo.sam.core.publishers.DefaultFilenameFactory;
import eu.mondo.sam.core.publishers.FilenameFactory;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.PhaseResult;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationLogic;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

public final class BenchmarkRunner<TMatch, TElement, TDriver extends Driver<TElement>, TBenchmarkConfig extends BenchmarkConfig, TChecker extends Checker<TMatch>> {

	protected Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
	protected final TBenchmarkConfig benchmarkConfig;
	protected final AbstractBenchmarkCase<TMatch, TElement, TDriver, TBenchmarkConfig, TChecker> benchmarkCase;

	protected Transformation<?, ?> transformation;
	protected TransformationLogic<TMatch, TElement, ?, TBenchmarkConfig> transformationLogic;
	protected TDriver driver;
	protected List<TChecker> checkers;
	protected List<Collection<TMatch>> matches;

	public BenchmarkRunner(final TBenchmarkConfig benchmarkConfig,
			final AbstractBenchmarkCase<TMatch, TElement, TDriver, TBenchmarkConfig, TChecker> benchmarkCase) {
		this.benchmarkConfig = benchmarkConfig;
		this.benchmarkCase = benchmarkCase;
	}

	public BenchmarkResult runBenchmark() throws Exception {
		@SuppressWarnings("rawtypes")
		final TrainBenchmarkScenario scenario = ScenarioFactory.getScenario(benchmarkConfig.getScenario());

		scenario.setBenchmarkConfig(benchmarkConfig);
		scenario.initializeDescriptor();

		BenchmarkResult result = null;
		for (int i = 1; i <= benchmarkConfig.getRuns(); i++) {
			final BenchmarkEngine engine = new BenchmarkEngine();

			scenario.setRunIndex(i);
			final FilenameFactory factory = new DefaultFilenameFactory(scenario.getCaseDescriptor());
			result = new BenchmarkResult(new File("."));
			CsvPublisher publisher = new CsvPublisher(factory);
			result.addPublisher(publisher);

			final TrainBenchmarkDataToken token = new TrainBenchmarkDataToken();
			token.setBenchmarkRunner(this);

			driver = benchmarkCase.createDriver(benchmarkConfig);

			// initialize checkers
			checkers = new ArrayList<>();

			for (final RailwayQuery query : benchmarkConfig.getQueries()) {
				final TChecker checker = benchmarkCase.createChecker(benchmarkConfig, driver, query);
				checkers.add(checker);
			}

			engine.runBenchmark(result, scenario, token);
		}

		return result;
	}

	// initialization methods

	public final void initializeTransformation() throws IOException {
		transformation = benchmarkCase.createTransformation(benchmarkConfig, driver, benchmarkConfig.getQuery());
		transformationLogic = (TransformationLogic<TMatch, TElement, ?, TBenchmarkConfig>) TransformationLogic
				.newInstance(benchmarkConfig.getScenario(), getComparator());
		if (transformationLogic != null) {
			transformationLogic.initialize(benchmarkConfig, driver, random);
		}
		transformationLogic.setTransformation(transformation);
	}

	// phases

	public final void read(final PhaseResult phaseResult) throws Exception {
		final TimeMetric timer = new TimeMetric("Time");
		timer.startMeasure();
		driver.read(benchmarkConfig.getModelPathWithoutExtension());
		timer.stopMeasure();
		phaseResult.addMetrics(timer);

		final ScalarMetric maxMemory = new ScalarMetric("MaxMemory");
		maxMemory.setValue(benchmarkConfig.getMaxMemory());
		phaseResult.addMetrics(maxMemory);
	}

	public final void check(final PhaseResult phaseResult) throws Exception {
		// initialize a list for the matches
		matches = new ArrayList<>(checkers.size());

		final TimeMetric timer = new TimeMetric("Time");
		final ScalarMetric results = new ScalarMetric("Matches");
		timer.startMeasure();

		for (final TChecker checker : checkers) {
			matches.add(checker.check());
		}

		timer.stopMeasure();
		// only use the first match for now
		results.setValue(matches.get(0).size());
		phaseResult.addMetrics(timer, results);
	}

	public final void destroy() throws Exception {
		for (final TChecker checker : checkers) {
			checker.destroy();
		}
		if (driver != null) {
			driver.destroy();
		}
	}

	public final void transform(final PhaseResult phaseResult) throws Exception {
		transformationLogic.performTransformation(phaseResult, matches.get(0));
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
