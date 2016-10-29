package hu.bme.mit.trainbenchmark.benchmark.sesame;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;

public class SesameBenchmarkScenario
		extends BenchmarkScenario<SesameMatch, SesameDriver, SesameBenchmarkConfig> {

	public SesameBenchmarkScenario(final SesameBenchmarkConfig sbc) throws Exception {
		super(new SesameDriverFactory(sbc.isInferencing()), new SesameModelOperationFactory<SesameDriver>(), new SesameMatchComparator(), sbc);
	}

}
