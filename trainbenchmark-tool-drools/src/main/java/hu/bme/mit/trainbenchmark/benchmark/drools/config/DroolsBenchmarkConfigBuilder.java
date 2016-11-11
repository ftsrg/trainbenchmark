package hu.bme.mit.trainbenchmark.benchmark.drools.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class DroolsBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<DroolsBenchmarkConfig, DroolsBenchmarkConfigBuilder> {

	@Override
	public DroolsBenchmarkConfig createConfig() {
		checkNotNulls();
		return new DroolsBenchmarkConfig(configBase);
	}

}
