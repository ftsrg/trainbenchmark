package hu.bme.mit.trainbenchmark.benchmark.allegro;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;

public class AllegroBenchmarkLogic extends GenericBenchmarkLogic{

	public AllegroBenchmarkLogic(String[] args) throws ParseException {
		super(args);
		bc = new BenchmarkConfig(args);
	}

	@Override
	protected String getPackageName() {
		return "allegro";
	}

}
