package hu.bme.mit.trainbenchmark.benchmark.jena.config;

import hu.bme.mit.trainbenchmark.benchmark.rdf.config.RdfBenchmarkConfigBuilder;

public class JenaBenchmarkConfigBuilder
		extends RdfBenchmarkConfigBuilder<JenaBenchmarkConfig, JenaBenchmarkConfigBuilder> {

	@Override
	public JenaBenchmarkConfig createConfig() {
		checkNotNulls();
		return new JenaBenchmarkConfig(configBase, inferencing, format);
	}

}
