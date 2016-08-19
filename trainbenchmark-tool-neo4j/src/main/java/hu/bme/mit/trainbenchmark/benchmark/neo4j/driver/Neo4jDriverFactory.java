package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class Neo4jDriverFactory extends DriverFactory<Neo4jDriver> {

	protected final String modelDir;
	
	public Neo4jDriverFactory(final String modelDir) {
		super();
		this.modelDir = modelDir;
	}
	
	@Override
	public Neo4jDriver createInstance() throws Exception {
		return new Neo4jDriver(modelDir);
	}

}
