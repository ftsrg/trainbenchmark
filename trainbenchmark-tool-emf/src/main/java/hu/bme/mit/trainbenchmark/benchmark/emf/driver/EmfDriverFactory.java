package hu.bme.mit.trainbenchmark.benchmark.emf.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class EmfDriverFactory extends DriverFactory<EmfDriver> {

	@Override
	public EmfDriver createInstance() throws Exception {
		return new EmfDriver();
	}

}
