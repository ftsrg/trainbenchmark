package hu.bme.mit.trainbenchmark.benchmark.orientdb.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.OrientDbBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class OrientDbBenchmarkInitializer extends TestBenchmarkInitializer<OrientDbBenchmarkLogic>{

	@Override
	protected OrientDbBenchmarkLogic initializeBenchmark(final Query query, final Scenario scenario) {
		final BenchmarkConfig bc = new BenchmarkConfig("OrientDb", scenario,  size, runIndex, query, iterationCount, modificationMethod, modificationConstant);
		return new OrientDbBenchmarkLogic(bc);
	}

}
