package hu.bme.mit.trainbenchmark.benchmark.rdf4j.test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.rdf.tests.RdfTest;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.Rdf4jBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;

@RunWith(Parameterized.class)
public class Rdf4jTest extends RdfTest {
	
	@Override
	protected BenchmarkResult runTest(BenchmarkConfigCore bcc) throws Exception {
		final Rdf4jBenchmarkConfigWrapper bcw = new Rdf4jBenchmarkConfigWrapper(bcc, inferencing);
		final Rdf4jBenchmarkScenario scenario = new Rdf4jBenchmarkScenario(bcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
