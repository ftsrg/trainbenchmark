package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;

public class Neo4jDriverFactory extends DriverFactory<Neo4jDriver> {

	protected final String modelDir;
	protected final Neo4jGraphFormat graphFormat;

	public Neo4jDriverFactory(final String modelDir, final Neo4jGraphFormat graphFormat) {
		super();
		this.modelDir = modelDir;
		this.graphFormat = graphFormat;
	}

	@Override
	public Neo4jDriver createInstance() throws Exception {
		return new Neo4jDriver(modelDir, graphFormat);
	}

}
