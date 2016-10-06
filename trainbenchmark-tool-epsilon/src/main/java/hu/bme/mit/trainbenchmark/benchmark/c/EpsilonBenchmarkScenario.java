package hu.bme.mit.trainbenchmark.benchmark.c;

import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EpsilonBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.operations.EpsilonModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class EpsilonBenchmarkScenario
		extends BenchmarkScenario<EmfMatch, EmfDriver, EpsilonBenchmarkConfigWrapper> {

	public EpsilonBenchmarkScenario(final EpsilonBenchmarkConfigWrapper bcw) throws Exception {
		super(new EmfDriverFactory(), new EpsilonModelOperationFactory<EmfDriver>(), new EmfMatchComparator(), bcw);
	}

}
