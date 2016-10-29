package hu.bme.mit.trainbenchmark.benchmark.jena;

import hu.bme.mit.trainbenchmark.benchmark.jena.comparators.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.operations.JenaModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class JenaBenchmarkScenario extends BenchmarkScenario<JenaMatch, JenaDriver, JenaBenchmarkConfig> {

	public JenaBenchmarkScenario(final JenaBenchmarkConfig bc) throws Exception {
		super(new JenaDriverFactory(bc.isInferencing()), new JenaModelOperationFactory(), new JenaMatchComparator(), bc);
	}

}
