package hu.bme.mit.trainbenchmark.benchmark.sesame.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class SesameBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<SesameBenchmarkConfigBuilder, SesameBenchmarkConfig> {

	@Override
	public SesameBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new SesameBenchmarkConfig(configBase, inferencing);
	}

}
