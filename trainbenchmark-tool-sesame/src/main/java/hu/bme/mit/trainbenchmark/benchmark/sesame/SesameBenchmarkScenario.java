package hu.bme.mit.trainbenchmark.benchmark.sesame;

import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.comparators.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.operations.SesameModelOperationFactory;

public class SesameBenchmarkScenario
		extends BenchmarkScenario<SesameMatch, SesameDriver, SesameBenchmarkConfigWrapper> {

	public SesameBenchmarkScenario(final SesameBenchmarkConfigWrapper sbcw) throws Exception {
		super(new SesameDriverFactory(sbcw.isInferencing()), new SesameModelOperationFactory<SesameDriver>(), new SesameMatchComparator(), sbcw);
	}

}
