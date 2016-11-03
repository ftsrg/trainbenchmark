package hu.bme.mit.trainbenchmark.benchmark.config;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;
import hu.bme.mit.trainbenchmark.config.ExecutionConfig;

public abstract class BenchmarkConfig extends AbstractConfig<BenchmarkConfigBase> {

	public BenchmarkConfig(final BenchmarkConfigBase configBase, final ExecutionConfig executionConfig) {
		super(configBase, executionConfig);
	}

	/**
	 * @return The name of the tools for storing the benchmark results. Example: "RDF4J (No Inferencing)"
	 */
	public abstract String getToolName();

	/**
	 * @return The name of the project to be executed. Example: "sesame"
	 */
	public abstract String getProjectName();

	public String getDescription() {
		return "";
	}

}
