package hu.bme.mit.trainbenchmark.benchmark.virtuoso.test;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkInitializer;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.VirtuosoBenchmarkLogic;

public class VirtuosoBenchmarkInitializer extends BenchmarkInitializer{

	@Override
	protected GenericBenchmarkLogic initializeBenchmark(String queryName,
			String scenario) throws ParseException {
		// @formatter:off
		String[] args = {
				"-query", queryName, 
				"-benchmarkArtifact", "../models/railway-test-1.ttl",
				"-scenario", scenario, 
				"-workspacePath", "../" 
			};
		// @formatter:on
		return new VirtuosoBenchmarkLogic(args);
	}

}
