package hu.bme.mit.trainbenchmark.benchmark.janusgraph.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class JanusGraphBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<JanusGraphBenchmarkConfig, JanusGraphBenchmarkConfigBuilder> {

	@Override
	public JanusGraphBenchmarkConfig createConfig() {
		checkNotNulls();
		return new JanusGraphBenchmarkConfig(configBase);
	}
}
