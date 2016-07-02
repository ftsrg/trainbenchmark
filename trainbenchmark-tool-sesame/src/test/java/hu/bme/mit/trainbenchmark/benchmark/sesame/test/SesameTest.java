package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkBaseTest;

public abstract class SesameTest extends BenchmarkBaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final SesameBenchmarkConfigWrapper sbcw = createSesameBenchmarkConfigWrapper(bc);
		final SesameBenchmarkScenario scenario = new SesameBenchmarkScenario(sbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

	protected abstract SesameBenchmarkConfigWrapper createSesameBenchmarkConfigWrapper(final BenchmarkConfigCore bc);

}
