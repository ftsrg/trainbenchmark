package hu.bme.mit.trainbenchmark.benchmark.blazegraph;

import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.comparators.Rdf4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.operations.Rdf4jModelOperationFactory;

public class BlazegraphBenchmarkScenario extends BenchmarkScenario<Rdf4jMatch, Rdf4jDriver, BlazegraphBenchmarkConfigWrapper> {

	public BlazegraphBenchmarkScenario(final BlazegraphBenchmarkConfigWrapper bbcw) throws Exception {
		super(BlazegraphDriver.create(bbcw.isInferencing()), Rdf4jModelOperationFactory.create(), Rdf4jMatchComparator.create(),
				bbcw);
	}

}
