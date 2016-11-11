package hu.bme.mit.trainbenchmark.benchmark.mysql.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class MySqlBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<MySqlBenchmarkConfig, MySqlBenchmarkConfigBuilder> {

	@Override
	public MySqlBenchmarkConfig createConfig() {
		checkNotNulls();
		return new MySqlBenchmarkConfig(configBase);
	}
}
