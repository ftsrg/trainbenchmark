package hu.bme.mit.trainbenchmark.benchmark.drools6;

import hu.bme.mit.trainbenchmark.benchmark.drools6.config.Drools6BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.drools6.driver.Drools6Driver;
import hu.bme.mit.trainbenchmark.benchmark.drools6.operations.Drools6ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class Drools6BenchmarkScenario
		extends BenchmarkScenario<EmfMatch, Drools6Driver, Drools6BenchmarkConfigWrapper> {

	public Drools6BenchmarkScenario(final Drools6BenchmarkConfigWrapper dbcw) throws Exception {
		super(Drools6Driver.create(), Drools6ModelOperationFactory.create(), EmfMatchComparator.create(), dbcw);
	}

}
