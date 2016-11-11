package hu.bme.mit.trainbenchmark.generator.emf.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;

public class EmfGeneratorConfigBuilder
		extends GeneratorConfigBuilder<EmfGeneratorConfig, EmfGeneratorConfigBuilder> {

	@Override
	public EmfGeneratorConfig createConfig() {
		checkNotNulls();
		return new EmfGeneratorConfig(configBase);
	}

}
