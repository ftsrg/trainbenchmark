package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;

public class SesameNoInferencingTest extends SesameTest {

	@Override
	protected RdfBenchmarkConfigWrapper createRdfBenchmarkConfigWrapper(final BenchmarkConfig bc) {
		return new RdfBenchmarkConfigWrapper(bc, false);
	}

}
