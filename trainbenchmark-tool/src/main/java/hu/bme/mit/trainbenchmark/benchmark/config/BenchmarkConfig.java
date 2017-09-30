package hu.bme.mit.trainbenchmark.benchmark.config;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;

public abstract class BenchmarkConfig extends AbstractConfig<BenchmarkConfigBase> {

	protected BenchmarkConfig(final BenchmarkConfigBase configBase) {
		super(configBase);
	}

	/**
	 * @return The name of the tools for storing the benchmark results. Example: "Sesame (No Inferencing)"
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
