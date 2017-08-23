package hu.bme.mit.trainbenchmark.benchmark.graphflow.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class GraphflowDriverFactory extends DriverFactory<GraphflowDriver> {

	protected final String modelDir;

	public GraphflowDriverFactory(final String modelDir) {
		super();
		this.modelDir = modelDir;
	}

	@Override
	public GraphflowDriver createInstance() throws Exception {
		return new GraphflowDriver(modelDir);
	}

}
