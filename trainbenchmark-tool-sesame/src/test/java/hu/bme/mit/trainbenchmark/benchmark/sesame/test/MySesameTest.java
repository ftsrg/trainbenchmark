package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.test.BaseTest;

public class MySesameTest extends BaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final boolean inferencing = true;

		final RdfBenchmarkConfigWrapper rbcw = new RdfBenchmarkConfigWrapper(bc, inferencing);
		final SesameBenchmarkScenario scenario = new SesameBenchmarkScenario(rbcw);
		final BenchmarkResult result = scenario.runBenchmark();

		return result;
	}

}
