package hu.bme.mit.trainbenchmark.benchmark.epsilon;

import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.operations.EpsilonModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class EpsilonBenchmarkScenario
		extends BenchmarkScenario<EmfMatch, EmfDriver, EpsilonBenchmarkConfig> {

	public EpsilonBenchmarkScenario(final EpsilonBenchmarkConfig bc) throws Exception {
		super(new EmfDriverFactory(), new EpsilonModelOperationFactory<EmfDriver>(), new EmfMatchComparator(), bc);
	}

}
