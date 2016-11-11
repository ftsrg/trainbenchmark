package hu.bme.mit.trainbenchmark.benchmark.neo4j.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class Neo4jBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<Neo4jBenchmarkConfig, Neo4jBenchmarkConfigBuilder> {

	private Neo4jEngine engine;

	public Neo4jBenchmarkConfigBuilder setEngine(final Neo4jEngine engine) {
		this.engine = engine;
		return this;
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(engine);
	}

	@Override
	public Neo4jBenchmarkConfig createConfig() {
		checkNotNulls();
		return new Neo4jBenchmarkConfig(configBase, engine);
	}
}
