package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class TinkerGraphBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<TinkerGraphBenchmarkConfigBuilder, TinkerGraphBenchmarkConfig> {

	@Override
	public TinkerGraphBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new TinkerGraphBenchmarkConfig(configBase);
	}
}
