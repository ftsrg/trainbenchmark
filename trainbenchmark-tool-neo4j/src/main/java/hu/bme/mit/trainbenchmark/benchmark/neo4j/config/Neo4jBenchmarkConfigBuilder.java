package hu.bme.mit.trainbenchmark.benchmark.neo4j.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;

public class Neo4jBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<Neo4jBenchmarkConfig, Neo4jBenchmarkConfigBuilder> {

	private Neo4jDeployment deployment;
	private Neo4jEngine engine;
	private Neo4jGraphFormat graphFormat;

	public Neo4jBenchmarkConfigBuilder setDeployment(Neo4jDeployment deployment) {
		this.deployment = deployment;
		return this;
	}

	public Neo4jBenchmarkConfigBuilder setEngine(final Neo4jEngine engine) {
		this.engine = engine;
		return this;
	}

	public Neo4jBenchmarkConfigBuilder setGraphFormat(final Neo4jGraphFormat graphFormat) {
		this.graphFormat = graphFormat;
		return this;
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(deployment);
		Preconditions.checkNotNull(engine);
		Preconditions.checkNotNull(graphFormat);
	}

	@Override
	public Neo4jBenchmarkConfig createConfig() {
		checkNotNulls();
		return new Neo4jBenchmarkConfig(configBase, deployment, engine, graphFormat);
	}
}
