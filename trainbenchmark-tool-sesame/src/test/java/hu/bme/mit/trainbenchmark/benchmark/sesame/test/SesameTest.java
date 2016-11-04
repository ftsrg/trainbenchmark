package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.tests.RdfTest;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfig;

@RunWith(Parameterized.class)
public class SesameTest extends RdfTest {
	
	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final SesameBenchmarkConfig bc = new SesameBenchmarkConfig(bcb, inferencing);
		final SesameBenchmarkScenario scenario = new SesameBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
