package hu.bme.mit.trainbenchmark.benchmark.janusgraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class JanusGraphDriverFactory extends DriverFactory<JanusGraphDriver> {

	@Override
	public JanusGraphDriver createInstance() throws Exception {
		return new JanusGraphDriver();
	}

}
