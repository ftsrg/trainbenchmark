package hu.bme.mit.trainbenchmark.benchmark.orientdb;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public class OrientDbBenchmarkLogic extends AbstractBenchmarkLogic {
	
	public OrientDbBenchmarkLogic(final String[] args) throws ParseException {
		bc = new BenchmarkConfig(args, "OrientDb");
	}

	public OrientDbBenchmarkLogic(final BenchmarkConfig bc) {
		super(bc);
	}
	
}
