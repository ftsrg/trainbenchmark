package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfigWrapper;

public class SesameInferencingTest extends SesameTest {

	@Override
	protected SesameBenchmarkConfigWrapper createSesameBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new SesameBenchmarkConfigWrapper(bc, true);
	}

}
