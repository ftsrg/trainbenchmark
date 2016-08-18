package hu.bme.mit.trainbenchmark.benchmark.rdf4j;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.comparators.Rdf4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.operations.Rdf4jModelOperationFactory;

public class Rdf4jBenchmarkScenario extends BenchmarkScenario<Rdf4jMatch, Rdf4jDriver, Rdf4jBenchmarkConfigWrapper> {

	public Rdf4jBenchmarkScenario(final Rdf4jBenchmarkConfigWrapper sbcw) throws Exception {
		super(Rdf4jDriver.create(sbcw.isInferencing()), Rdf4jModelOperationFactory.create(),
				Rdf4jMatchComparator.create(), sbcw);
	}

}
