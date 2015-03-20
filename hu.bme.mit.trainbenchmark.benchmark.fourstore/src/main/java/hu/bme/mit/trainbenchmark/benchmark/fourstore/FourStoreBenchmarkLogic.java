package hu.bme.mit.trainbenchmark.benchmark.fourstore;

import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

import org.apache.commons.cli.ParseException;

public class FourStoreBenchmarkLogic extends AbstractBenchmarkLogic {

	protected FourStoreBenchmarkConfig fsbc; 
	
	public FourStoreBenchmarkLogic(final String[] args) throws ParseException {
		bc = fsbc = new FourStoreBenchmarkConfig(args, getTool());
	}

	public FourStoreBenchmarkLogic(final FourStoreBenchmarkConfig fsbc) {
		super(fsbc);
		this.fsbc = fsbc;
	}
	
	@Override
	protected String getTool() {
		return "FourStore";
	}
	
}
