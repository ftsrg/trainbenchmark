package hu.bme.mit.trainbenchmark.benchmark.jena;

import hu.bme.mit.trainbenchmark.benchmark.jena.comparators.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.operations.JenaModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class JenaBenchmarkScenario extends BenchmarkScenario<JenaMatch, JenaDriver, JenaBenchmarkConfigWrapper> {

	public JenaBenchmarkScenario(final JenaBenchmarkConfigWrapper bcw) throws Exception {
		super(new JenaDriverFactory(bcw.isInferencing()), new JenaModelOperationFactory(), new JenaMatchComparator(), bcw);
	}

}
