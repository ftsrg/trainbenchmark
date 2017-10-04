package hu.bme.mit.trainbenchmark.benchmark.orientdb.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class OrientDbDriverFactory extends DriverFactory<OrientDbDriver> {

	@Override
	public OrientDbDriver createInstance() throws Exception {
		return new OrientDbDriver();
	}

}
