package hu.bme.mit.trainbenchmark.benchmark.rdf4j;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.comparators.Rdf4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.operations.Rdf4jModelOperationFactory;

public class Rdf4jBenchmarkScenario extends BenchmarkScenario<Rdf4jMatch, Rdf4jDriver, Rdf4jBenchmarkConfig> {

	public Rdf4jBenchmarkScenario(final Rdf4jBenchmarkConfig bc) throws Exception {
		super(new Rdf4jDriverFactory(bc.isInferencing()), new Rdf4jModelOperationFactory<Rdf4jDriver>(), new Rdf4jMatchComparator(), bc);
	}

}
