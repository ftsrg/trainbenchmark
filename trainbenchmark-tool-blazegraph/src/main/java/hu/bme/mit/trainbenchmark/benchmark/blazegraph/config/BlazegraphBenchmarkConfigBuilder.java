package hu.bme.mit.trainbenchmark.benchmark.blazegraph.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class BlazegraphBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<BlazegraphBenchmarkConfigBuilder, BlazegraphBenchmarkConfig> {

	@Override
	public BlazegraphBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new BlazegraphBenchmarkConfig(configBase, inferencing);
	}

}
