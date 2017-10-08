package hu.bme.mit.trainbenchmark.benchmark.sesame.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class SesameBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<SesameBenchmarkConfig, SesameBenchmarkConfigBuilder> {

	@Override
	public SesameBenchmarkConfig createConfig() {
		checkNotNulls();
		return new SesameBenchmarkConfig(configBase, inferencing, format);
	}

}
