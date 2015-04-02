package hu.bme.mit.trainbenchmark.benchmark.orientdb.test;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.OrientDbBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkInitializer;

import org.apache.commons.cli.ParseException;

public class OrientDbBenchmarkInitializer extends BenchmarkInitializer {

	@Override
	protected GenericBenchmarkLogic initializeBenchmark(String queryName, String scenario) throws ParseException {
		// @formatter:off
		String[] args = {
				"-query", queryName,
				"-benchmarkArtifact", "../models/railway-orienttest-1.graphml",
				"-scenario", scenario, 
				"-workspacePath", "../" 
			};
		// @formatter:on
		
		return new OrientDbBenchmarkLogic(args);
	}

}
