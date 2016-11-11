package hu.bme.mit.trainbenchmark.benchmark.config;

import hu.bme.mit.trainbenchmark.config.AbstractConfigBuilder;

public abstract class BenchmarkConfigBuilder
	<TBenchmarkConfig extends BenchmarkConfig, TBenchmarkConfigBuilder extends BenchmarkConfigBuilder<TBenchmarkConfig, ?>>
	extends AbstractConfigBuilder
	<BenchmarkConfigBase, TBenchmarkConfig, TBenchmarkConfigBuilder> {

}
