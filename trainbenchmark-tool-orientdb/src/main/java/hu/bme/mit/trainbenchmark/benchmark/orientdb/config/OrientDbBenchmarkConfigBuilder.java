
package hu.bme.mit.trainbenchmark.benchmark.orientdb.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class OrientDbBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<OrientDbConfig, OrientDbBenchmarkConfigBuilder> {

	@Override
	public OrientDbConfig createConfig() {
		checkNotNulls();
		return new OrientDbConfig(configBase);
	}
}
