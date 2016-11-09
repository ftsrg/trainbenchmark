package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class EclipseOclBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<EclipseOclBenchmarkConfigBuilder, EclipseOclBenchmarkConfig> {

	@Override
	public EclipseOclBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new EclipseOclBenchmarkConfig(configBase);
	}

}
