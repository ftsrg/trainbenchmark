package hu.bme.mit.trainbenchmark.benchmark.drools;

import hu.bme.mit.trainbenchmark.benchmark.drools.config.DroolsBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.drools.driver.DroolsDriver;
import hu.bme.mit.trainbenchmark.benchmark.drools.driver.DroolsDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.drools.operations.DroolsModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class DroolsBenchmarkScenario
		extends BenchmarkScenario<EmfMatch, DroolsDriver, DroolsBenchmarkConfigWrapper> {

	public DroolsBenchmarkScenario(final DroolsBenchmarkConfigWrapper bcw) throws Exception {
		super(new DroolsDriverFactory(), new DroolsModelOperationFactory(), new EmfMatchComparator(), bcw);
	}

}
