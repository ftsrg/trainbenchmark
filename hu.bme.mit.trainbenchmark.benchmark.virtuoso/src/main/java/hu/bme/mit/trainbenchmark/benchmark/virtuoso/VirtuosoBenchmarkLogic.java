package hu.bme.mit.trainbenchmark.benchmark.virtuoso;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.config.VirtuosoBenchmarkConfig;

public class VirtuosoBenchmarkLogic extends GenericBenchmarkLogic{

	VirtuosoBenchmarkConfig virtuosoBenchmarkConfig;
	
	public VirtuosoBenchmarkLogic(String[] args) throws ParseException {
		super(args);
		bc = virtuosoBenchmarkConfig = new VirtuosoBenchmarkConfig(args);
	}

	@Override
	protected String getPackageName() {
		return "virtuoso";
	}

}
