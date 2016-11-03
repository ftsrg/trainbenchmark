package hu.bme.mit.trainbenchmark.generator.config;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;
import hu.bme.mit.trainbenchmark.config.ExecutionConfig;

public abstract class GeneratorConfig extends AbstractConfig<GeneratorConfigBase> {

	public GeneratorConfig(final GeneratorConfigBase configBase, final ExecutionConfig executionConfig) {
		super(configBase, executionConfig);
	}

	/**
	 * @return The name of the project to be executed. Example: "emf"
	 */
	public abstract String getProjectName();

}
