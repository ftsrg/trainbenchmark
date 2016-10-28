package hu.bme.mit.trainbenchmark.benchmark.viatra.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;

public class ViatraDriverFactory extends DriverFactory<ViatraDriver> {

	protected final ViatraBackend backend;
	
	public ViatraDriverFactory(final ViatraBackend backend) {
		super();
		this.backend = backend;
	}
	
	@Override
	public ViatraDriver createInstance() throws Exception {
		return new ViatraDriver(backend);
	}

}
