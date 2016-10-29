package hu.bme.mit.trainbenchmark.benchmark.blazegraph;

import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;

public class BlazegraphBenchmarkScenario extends BenchmarkScenario<SesameMatch, BlazegraphDriver, BlazegraphBenchmarkConfig> {

	public BlazegraphBenchmarkScenario(final BlazegraphBenchmarkConfig bc) throws Exception {
		super(new BlazegraphDriverFactory(bc.isInferencing()), new SesameModelOperationFactory<BlazegraphDriver>(), new SesameMatchComparator(), bc);
	}

}
