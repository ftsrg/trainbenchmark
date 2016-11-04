package hu.bme.mit.trainbenchmark.generator.config;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;

public abstract class GeneratorConfig extends AbstractConfig<GeneratorConfigBase> {

	public GeneratorConfig(final GeneratorConfigBase configBase) {
		super(configBase);
	}

	/**
	 * @return The name of the project to be executed. Example: "emf"
	 */
	public abstract String getProjectName();

}
