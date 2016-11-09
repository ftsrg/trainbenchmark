package hu.bme.mit.trainbenchmark.benchmark.rdf4j.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class Rdf4jBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<Rdf4jBenchmarkConfigBuilder, Rdf4jBenchmarkConfig> {

	@Override
	public Rdf4jBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new Rdf4jBenchmarkConfig(configBase, inferencing);
	}

}
