package hu.bme.mit.trainbenchmark.benchmark.viatra.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class ViatraDriverFactory extends DriverFactory<ViatraDriver> {

	@Override
	public ViatraDriver createInstance() throws Exception {
		return new ViatraDriver();
	}

}
