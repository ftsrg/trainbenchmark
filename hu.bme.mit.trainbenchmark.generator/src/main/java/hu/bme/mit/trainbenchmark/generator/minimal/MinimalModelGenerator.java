package hu.bme.mit.trainbenchmark.generator.minimal;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public abstract class MinimalModelGenerator extends ModelGenerator {

	public MinimalModelGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

}
