package hu.bme.mit.trainbenchmark.benchmark.memsql;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public class MemSQLBenchmarkLogic extends AbstractBenchmarkLogic {

	public MemSQLBenchmarkLogic(final String[] args) throws ParseException {
		bc = new BenchmarkConfig(args, getTool());
	}

	public MemSQLBenchmarkLogic(final BenchmarkConfig bc) {
		super(bc);
	}

	@Override
	protected String getTool() {
		return "MemSQL";
	}

}
