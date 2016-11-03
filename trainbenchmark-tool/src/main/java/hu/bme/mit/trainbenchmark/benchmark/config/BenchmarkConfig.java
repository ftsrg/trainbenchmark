package hu.bme.mit.trainbenchmark.benchmark.config;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;

public abstract class BenchmarkConfig extends AbstractConfig<BenchmarkConfigBase> {

	protected BenchmarkConfig() {
	}

	public BenchmarkConfig(final BenchmarkConfigBase configBase) {
		this.configBase = configBase;
	}

	public BenchmarkConfigBase getConfigBase() {
		return configBase;
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
