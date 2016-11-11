package hu.bme.mit.trainbenchmark.generator.config;

import hu.bme.mit.trainbenchmark.config.AbstractConfigBuilder;

public abstract class GeneratorConfigBuilder
	<TGeneratorConfig extends GeneratorConfig,TGeneratorConfigBuilder extends GeneratorConfigBuilder<TGeneratorConfig, ?>>
	extends AbstractConfigBuilder
	<GeneratorConfigBase, TGeneratorConfig, TGeneratorConfigBuilder> {

}
