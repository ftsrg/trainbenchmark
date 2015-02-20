package hu.bme.mit.trainbenchmark.benchmark.fourstore;

import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public class FourStoreBenchmarkLogic extends GenericBenchmarkLogic {

	protected FourStoreBenchmarkConfig fsbc; 
	
	public FourStoreBenchmarkLogic(final String[] args) throws ParseException {
		super(args);
		bc = fsbc = new FourStoreBenchmarkConfig(args, getTool());
	}

	@Override
	protected String getTool() {
		return "FourStore";
	}
	
}
