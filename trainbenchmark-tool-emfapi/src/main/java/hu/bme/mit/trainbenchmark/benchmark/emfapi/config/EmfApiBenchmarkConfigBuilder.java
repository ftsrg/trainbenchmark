package hu.bme.mit.trainbenchmark.benchmark.emfapi.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class EmfApiBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<EmfApiBenchmarkConfig, EmfApiBenchmarkConfigBuilder> {

	@Override
	public EmfApiBenchmarkConfig createConfig() {
		checkNotNulls();
		return new EmfApiBenchmarkConfig(configBase);
	}

}
