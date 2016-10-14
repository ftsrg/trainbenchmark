package hu.bme.mit.trainbenchmark.benchmark.blazegraph;

import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;

public class BlazegraphBenchmarkScenario extends BenchmarkScenario<SesameMatch, BlazegraphDriver, BlazegraphBenchmarkConfigWrapper> {

	public BlazegraphBenchmarkScenario(final BlazegraphBenchmarkConfigWrapper bcw) throws Exception {
		super(new BlazegraphDriverFactory(bcw.isInferencing()), new SesameModelOperationFactory<BlazegraphDriver>(), new SesameMatchComparator(), bcw);
	}

}
