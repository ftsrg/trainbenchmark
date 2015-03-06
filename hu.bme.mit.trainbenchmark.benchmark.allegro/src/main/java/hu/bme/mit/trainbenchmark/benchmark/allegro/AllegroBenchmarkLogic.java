package hu.bme.mit.trainbenchmark.benchmark.allegro;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;
import hu.bme.mit.trainbenchmark.rdf.RDFBenchmarkConfig;

public class AllegroBenchmarkLogic extends GenericBenchmarkLogic{

	public AllegroBenchmarkLogic(String[] args) throws ParseException {
		super(args);
		bc = new RDFBenchmarkConfig(args, getTool());
	}

	@Override
	protected String getTool() {
		return "Allegro";
	}

}
