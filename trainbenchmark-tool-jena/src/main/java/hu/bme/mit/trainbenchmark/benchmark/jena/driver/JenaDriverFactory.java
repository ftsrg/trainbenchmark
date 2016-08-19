package hu.bme.mit.trainbenchmark.benchmark.jena.driver;

import hu.bme.mit.trainbenchmark.benchmark.rdf.driver.RdfDriverFactory;

public class JenaDriverFactory extends RdfDriverFactory<JenaDriver> {

	public JenaDriverFactory(final boolean inferencing) {
		super(inferencing);
	}

	@Override
	public JenaDriver createInstance() throws Exception {
		return new JenaDriver(inferencing);
	}

}
