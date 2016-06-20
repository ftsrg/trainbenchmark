package hu.bme.mit.trainbenchmark.benchmark.jena.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.jena.config.JenaBenchmarkConfigWrapper;

public class JenaNoInferencingTest extends JenaTest {

	@Override
	protected JenaBenchmarkConfigWrapper createJenaBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new JenaBenchmarkConfigWrapper(bc, false);
	}

}
