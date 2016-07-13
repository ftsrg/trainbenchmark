package hu.bme.mit.trainbenchmark.benchmark.sesame.test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import hu.bme.mit.trainbenchmark.benchmark.rdf.tests.RdfTest;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sesame.config.SesameBenchmarkConfigWrapper;

@RunWith(Parameterized.class)
public class SesameTest extends RdfTest {
	
	@Override
	protected BenchmarkResult runTest() throws Exception {
		final SesameBenchmarkConfigWrapper sbcw = new SesameBenchmarkConfigWrapper(bc, inferencing);
		final SesameBenchmarkScenario scenario = new SesameBenchmarkScenario(sbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
