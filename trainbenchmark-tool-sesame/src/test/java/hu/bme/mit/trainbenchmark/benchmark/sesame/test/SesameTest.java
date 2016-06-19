package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.test.BaseTest;

public abstract class SesameTest extends BaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final RdfBenchmarkConfigWrapper rbcw = createRdfBenchmarkConfigWrapper(bc);
		final SesameBenchmarkScenario scenario = new SesameBenchmarkScenario(rbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

	protected abstract RdfBenchmarkConfigWrapper createRdfBenchmarkConfigWrapper(final BenchmarkConfigCore bc);

}
