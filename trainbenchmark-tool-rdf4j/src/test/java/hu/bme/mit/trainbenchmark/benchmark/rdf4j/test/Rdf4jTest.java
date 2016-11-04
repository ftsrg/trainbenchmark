package hu.bme.mit.trainbenchmark.benchmark.rdf4j.test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.tests.RdfTest;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.Rdf4jBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;

@RunWith(Parameterized.class)
public class Rdf4jTest extends RdfTest {
	
	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final Rdf4jBenchmarkConfig bc = new Rdf4jBenchmarkConfig(bcb, inferencing);
		final Rdf4jBenchmarkScenario scenario = new Rdf4jBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
