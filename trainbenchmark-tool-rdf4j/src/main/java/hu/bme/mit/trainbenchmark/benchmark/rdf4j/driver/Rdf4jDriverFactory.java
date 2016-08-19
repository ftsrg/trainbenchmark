package hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver;

import hu.bme.mit.trainbenchmark.benchmark.rdf.driver.RdfDriverFactory;

public class Rdf4jDriverFactory extends RdfDriverFactory<Rdf4jDriver> {

	public Rdf4jDriverFactory(final boolean inferencing) {
		super(inferencing);
	}

	@Override
	public Rdf4jDriver createInstance() throws Exception {
		return new Rdf4jDriver(inferencing);
	}
	
}
