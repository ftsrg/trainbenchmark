package hu.bme.mit.trainbenchmark.benchmark.blazegraph;

import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;

public class BlazegraphBenchmarkScenario extends BenchmarkScenario<SesameMatch, SesameDriver, BlazegraphBenchmarkConfigWrapper> {

	public BlazegraphBenchmarkScenario(final BlazegraphBenchmarkConfigWrapper bbcw) throws Exception {
		super(BlazegraphDriver.create(bbcw.isInferencing()), SesameModelOperationFactory.create(), SesameMatchComparator.create(),
				bbcw);
	}

}
