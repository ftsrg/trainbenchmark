package hu.bme.mit.trainbenchmark.benchmark.blazegraph.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class BlazegraphBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<BlazegraphBenchmarkConfig, BlazegraphBenchmarkConfigBuilder> {

	@Override
	public BlazegraphBenchmarkConfig createConfig() {
		checkNotNulls();
		return new BlazegraphBenchmarkConfig(configBase, inferencing);
	}

}
