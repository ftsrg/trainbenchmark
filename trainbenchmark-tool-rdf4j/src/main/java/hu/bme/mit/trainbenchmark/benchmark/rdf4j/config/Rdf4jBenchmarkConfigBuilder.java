package hu.bme.mit.trainbenchmark.benchmark.rdf4j.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class Rdf4jBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<Rdf4jBenchmarkConfig, Rdf4jBenchmarkConfigBuilder> {

	@Override
	public Rdf4jBenchmarkConfig createConfig() {
		checkNotNulls();
		return new Rdf4jBenchmarkConfig(configBase, inferencing);
	}

}
