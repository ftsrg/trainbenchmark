package hu.bme.mit.trainbenchmark.benchmark.drools.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class DroolsDriverFactory extends DriverFactory<DroolsDriver> {

	@Override
	public DroolsDriver createInstance() throws Exception {
		return new DroolsDriver();
	}

}
