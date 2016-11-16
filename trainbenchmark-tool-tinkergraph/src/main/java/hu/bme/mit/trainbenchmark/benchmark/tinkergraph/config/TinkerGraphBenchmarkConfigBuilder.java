package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class TinkerGraphBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<TinkerGraphBenchmarkConfig, TinkerGraphBenchmarkConfigBuilder> {

	@Override
	public TinkerGraphBenchmarkConfig createConfig() {
		checkNotNulls();
		return new TinkerGraphBenchmarkConfig(configBase);
	}
}
