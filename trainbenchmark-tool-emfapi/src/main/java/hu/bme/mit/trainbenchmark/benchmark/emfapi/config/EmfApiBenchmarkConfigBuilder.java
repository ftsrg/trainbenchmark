package hu.bme.mit.trainbenchmark.benchmark.emfapi.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class EmfApiBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<EmfApiBenchmarkConfigBuilder, EmfApiBenchmarkConfig> {

	@Override
	public EmfApiBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new EmfApiBenchmarkConfig(configBase);
	}

}
