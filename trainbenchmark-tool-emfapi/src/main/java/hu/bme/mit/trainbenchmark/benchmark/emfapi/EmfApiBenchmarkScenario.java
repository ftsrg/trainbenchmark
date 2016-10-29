package hu.bme.mit.trainbenchmark.benchmark.emfapi;

import hu.bme.mit.trainbenchmark.benchmark.emf.comparators.EmfMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.operations.EmfApiModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class EmfApiBenchmarkScenario
		extends BenchmarkScenario<EmfMatch, EmfDriver, EmfApiBenchmarkConfig> {

	public EmfApiBenchmarkScenario(final EmfApiBenchmarkConfig bc) throws Exception {
		super(new EmfDriverFactory(), new EmfApiModelOperationFactory<EmfDriver>(), new EmfMatchComparator(), bc);
	}

}
