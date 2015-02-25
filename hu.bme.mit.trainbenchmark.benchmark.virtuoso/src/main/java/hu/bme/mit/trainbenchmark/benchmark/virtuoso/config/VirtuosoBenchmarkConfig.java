package hu.bme.mit.trainbenchmark.benchmark.virtuoso.config;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

public class VirtuosoBenchmarkConfig extends BenchmarkConfig{

	protected boolean inferencing;
	
	public VirtuosoBenchmarkConfig(String[] args) throws ParseException {
		super(args);
	}
	
	@Override
	protected void initOptions() {
		super.initOptions();
		options.addOption("inferencing", true, "RDF: type of inference");
	}
	
	@Override
	public void processArguments(String[] args) throws ParseException {
		super.processArguments(args);
		inferencing = cmd.hasOption("inferencing");
	}

	public boolean isInferencing() {
		return inferencing;
	}
}
