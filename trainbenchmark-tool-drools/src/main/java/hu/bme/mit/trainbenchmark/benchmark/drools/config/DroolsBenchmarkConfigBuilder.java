package hu.bme.mit.trainbenchmark.benchmark.drools.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class DroolsBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<DroolsBenchmarkConfigBuilder, DroolsBenchmarkConfig> {

	@Override
	public DroolsBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new DroolsBenchmarkConfig(configBase);
	}

}
