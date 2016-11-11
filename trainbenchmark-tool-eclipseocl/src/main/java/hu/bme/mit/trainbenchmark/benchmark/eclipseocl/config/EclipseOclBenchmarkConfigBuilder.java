package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class EclipseOclBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<EclipseOclBenchmarkConfig, EclipseOclBenchmarkConfigBuilder> {

	@Override
	public EclipseOclBenchmarkConfig createConfig() {
		checkNotNulls();
		return new EclipseOclBenchmarkConfig(configBase);
	}

}
