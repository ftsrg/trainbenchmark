package hu.bme.mit.trainbenchmark.benchmark.jena;

import hu.bme.mit.trainbenchmark.benchmark.jena.comparators.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.operations.JenaModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class JenaBenchmarkScenario extends BenchmarkScenario<JenaMatch, JenaDriver, JenaBenchmarkConfigWrapper> {

	public JenaBenchmarkScenario(final JenaBenchmarkConfigWrapper jbcw) throws Exception {
		super(JenaDriver.create(jbcw.isInferencing()), JenaModelOperationFactory.create(), JenaMatchComparator.create(),
				jbcw);
	}

}
