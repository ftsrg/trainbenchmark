package hu.bme.mit.trainbenchmark.benchmark.sesame.driver;

import hu.bme.mit.trainbenchmark.benchmark.rdf.driver.RdfDriverFactory;

public class SesameDriverFactory extends RdfDriverFactory<SesameDriver> {

	public SesameDriverFactory(final boolean inferencing) {
		super(inferencing);
	}

	@Override
	public SesameDriver createInstance() throws Exception {
		return new SesameDriver(inferencing);
	}
	
}
