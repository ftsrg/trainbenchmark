package hu.bme.mit.trainbenchmark.benchmark.sqlite.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class SQLiteBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<SQLiteBenchmarkConfig, SQLiteBenchmarkConfigBuilder> {

	@Override
	public SQLiteBenchmarkConfig createConfig() {
		checkNotNulls();
		return new SQLiteBenchmarkConfig(configBase);
	}

}
