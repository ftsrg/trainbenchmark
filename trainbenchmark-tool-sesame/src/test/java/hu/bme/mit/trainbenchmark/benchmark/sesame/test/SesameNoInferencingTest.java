package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfigWrapper;

public class SesameNoInferencingTest extends SesameTest {

	@Override
	protected SesameBenchmarkConfigWrapper createRdfBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new SesameBenchmarkConfigWrapper(bc, false);
	}

}
