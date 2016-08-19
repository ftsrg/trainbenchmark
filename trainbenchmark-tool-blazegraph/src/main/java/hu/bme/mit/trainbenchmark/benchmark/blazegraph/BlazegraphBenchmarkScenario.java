package hu.bme.mit.trainbenchmark.benchmark.blazegraph;

import hu.bme.mit.trainbenchmark.benchmark.blazegraph.config.BlazegraphBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.blazegraph.driver.BlazegraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.comparators.Rdf4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.operations.Rdf4jModelOperationFactory;

public class BlazegraphBenchmarkScenario extends BenchmarkScenario<Rdf4jMatch, BlazegraphDriver, BlazegraphBenchmarkConfigWrapper> {

	public BlazegraphBenchmarkScenario(final BlazegraphBenchmarkConfigWrapper bcw) throws Exception {
		super(new BlazegraphDriverFactory(bcw.isInferencing()), new Rdf4jModelOperationFactory<BlazegraphDriver>(), new Rdf4jMatchComparator(), bcw);
	}

}
