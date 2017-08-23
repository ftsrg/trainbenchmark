package hu.bme.mit.trainbenchmark.benchmark.graphflow.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class GraphflowBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<GraphflowBenchmarkConfig, GraphflowBenchmarkConfigBuilder> {

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
	}

	@Override
	public GraphflowBenchmarkConfig createConfig() {
		checkNotNulls();
		return new GraphflowBenchmarkConfig(configBase);
	}
}
