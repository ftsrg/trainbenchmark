package hu.bme.mit.trainbenchmark.benchmark.mysql.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class MySqlBenchmarkConfigBuilder extends BenchmarkConfigBuilder<MySqlBenchmarkConfigBuilder, MySqlBenchmarkConfig> {

	@Override
	public MySqlBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new MySqlBenchmarkConfig(configBase);
	}
}
