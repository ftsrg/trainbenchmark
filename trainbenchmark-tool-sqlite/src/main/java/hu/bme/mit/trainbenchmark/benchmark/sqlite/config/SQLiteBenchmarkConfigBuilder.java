package hu.bme.mit.trainbenchmark.benchmark.sqlite.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class SQLiteBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<SQLiteBenchmarkConfigBuilder, SQLiteBenchmarkConfig> {

	@Override
	public SQLiteBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new SQLiteBenchmarkConfig(configBase);
	}

}
