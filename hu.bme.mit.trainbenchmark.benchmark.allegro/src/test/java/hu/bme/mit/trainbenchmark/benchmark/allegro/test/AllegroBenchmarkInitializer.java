package hu.bme.mit.trainbenchmark.benchmark.allegro.test;

import hu.bme.mit.trainbenchmark.benchmark.allegro.AllegroBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkInitializer;

import org.apache.commons.cli.ParseException;

public class AllegroBenchmarkInitializer extends BenchmarkInitializer {

	@Override
	public GenericBenchmarkLogic initializeBenchmark(String queryName, String scenario) throws ParseException {
		// @formatter:off
		String[] args = {
				"-query", queryName, 
				"-benchmarkArtifact", "../models/railway-test-1.ttl",
				"-scenario", scenario, 
				"-workspacePath", "../" 
			};
		// @formatter:on
		
		return new AllegroBenchmarkLogic(args);
	}

}
