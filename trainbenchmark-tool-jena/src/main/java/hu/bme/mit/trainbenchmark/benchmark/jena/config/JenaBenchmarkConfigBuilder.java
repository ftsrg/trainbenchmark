package hu.bme.mit.trainbenchmark.benchmark.jena.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class JenaBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<JenaBenchmarkConfigBuilder, JenaBenchmarkConfig> {

	@Override
	public JenaBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new JenaBenchmarkConfig(configBase, inferencing);
	}

}
