package hu.bme.mit.trainbenchmark.benchmark.rdf.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public abstract class RdfDriverFactory<TRdfDriver extends RdfDriver> extends DriverFactory<TRdfDriver> {

	protected final boolean inferencing;

	public RdfDriverFactory(final boolean inferencing) {
		this.inferencing = inferencing;
	}
	
}
