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

package hu.bme.mit.trainbenchmark.benchmark;

import static hu.bme.mit.trainbenchmark.constants.ScenarioConstants.ANALYZE;
import static hu.bme.mit.trainbenchmark.constants.ScenarioConstants.DESCRIBE;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.publisher.AnalysisFilenameFactory;
import hu.bme.mit.trainbenchmark.benchmark.publisher.AnalysisJsonPublisher;
import hu.bme.mit.trainbenchmark.benchmark.publisher.ExtendedFilenameFactory;
import hu.bme.mit.trainbenchmark.benchmark.publisher.TrainBenchmarkCaseDescriptor;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.Scenario;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.ScenarioFactory;
import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import eu.mondo.sam.core.BenchmarkEngine;
import eu.mondo.sam.core.publishers.CommandLinePublisher;
import eu.mondo.sam.core.publishers.JsonPublisher;
import eu.mondo.sam.core.publishers.Publisher;
import eu.mondo.sam.core.results.BenchmarkResult;

public abstract class BenchmarkLogic {

	protected BenchmarkConfig bc;

	public BenchmarkLogic() {
	}

	public BenchmarkLogic(final BenchmarkConfig bc) {
		this.bc = bc;
	}

	@SuppressWarnings("unchecked")
	public BenchmarkResult runBenchmark() throws IOException {
		@SuppressWarnings("rawtypes")
		final Scenario scenario = ScenarioFactory.getScenario(bc.getScenario());
		final BenchmarkCase<?, ?, ?> benchmarkCase = getBenchmarkCase();
		benchmarkCase.setBenchmarkConfig(bc);

		scenario.setBenchmarkConfig(bc);
		scenario.setBenchmarkCase(benchmarkCase);
		scenario.initializeDescriptor();

		BenchmarkEngine engine = new BenchmarkEngine();
		TrainBenchmarkDataToken token = new TrainBenchmarkDataToken();
		BenchmarkResult result = new BenchmarkResult();
		TrainBenchmarkCaseDescriptor caseDescriptor = scenario.getCaseDescriptor();
		result.addAllPublishers(getPublishers(caseDescriptor));

		token.setBenchmarkCase(benchmarkCase);
		token.setConfig(bc);
		token.setDescriptor(caseDescriptor);

		for (int i = 1; i <= bc.getRunIndex(); i++) {
			scenario.setRunIndex(i);
			engine.runBenchmark(result, scenario, token);
		}
		return result;
	}

	public BenchmarkCase<?, ?, ?> getBenchmarkCase() {
		return getConcreteBenchmarkCase(this.getClass().getClassLoader());
	}

	protected BenchmarkCase<?, ?, ?> getConcreteBenchmarkCase(final ClassLoader classLoader) {
		try {
			// trying to loading generic class
			final String toolClassName = "hu.bme.mit.trainbenchmark.benchmark."
					+ bc.getClassName().toLowerCase() + "." + bc.getClassName()
					+ "BenchmarkCase";
			final Class<?> clazz = classLoader.loadClass(toolClassName);

			final int modifiers = clazz.getModifiers();
			// instantiate generic class if not abstract
			if (!Modifier.isAbstract(modifiers)) {
				return (BenchmarkCase<?, ?, ?>) clazz.newInstance();
			}

			// else instantiate specific class

			final String queryClassName = "hu.bme.mit.trainbenchmark.benchmark."
					+ bc.getClassName().toLowerCase() + "." + bc.getClassName()
					+ bc.getQuery();
			final Class<?> queryClass = classLoader.loadClass(queryClassName);

			// instantiate generic class if not abstract
			return (BenchmarkCase<?, ?, ?>) queryClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	protected List<Publisher> getPublishers(final TrainBenchmarkCaseDescriptor descriptor) {
		List<Publisher> publishers = new ArrayList<>();
		publishers.add(new CommandLinePublisher());
		if (bc.getScenario().equals(DESCRIBE)) {
			AnalysisFilenameFactory analysisFilenameFactory = new AnalysisFilenameFactory(
					descriptor);
			analysisFilenameFactory.setOverride(true);

			JsonPublisher describePublisher = new JsonPublisher(analysisFilenameFactory);
			describePublisher.setResultPath(bc.getDescribePath());
			publishers.add(describePublisher);
			return publishers;
		}

		publishers.add(new JsonPublisher(new ExtendedFilenameFactory(descriptor)));

		if (bc.getScenario().equals(ANALYZE)) {
			// don't override the results
			AnalysisJsonPublisher analysisPublisher = new AnalysisJsonPublisher(
					new AnalysisFilenameFactory(descriptor));
			analysisPublisher.setResultPath(bc.getAnalysisPath());
			publishers.add(analysisPublisher);
		}
		return publishers;
	}

	public BenchmarkConfig getBenchmarkConfig() {
		return bc;
	}

}
