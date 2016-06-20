package hu.bme.mit.trainbenchmark.benchmark.drools5;

import hu.bme.mit.trainbenchmark.benchmark.drools5.config.Drools5BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.benchmark.drools5.operations.Drools5ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class Drools5BenchmarkScenario
		extends BenchmarkScenario<EmfMatch, Drools5Driver, Drools5BenchmarkConfigWrapper> {

	public Drools5BenchmarkScenario(final Drools5BenchmarkConfigWrapper dbcw) throws Exception {
		super(Drools5Driver.create(), Drools5ModelOperationFactory.create(), EmfMatchComparator.create(), dbcw);
	}

}
