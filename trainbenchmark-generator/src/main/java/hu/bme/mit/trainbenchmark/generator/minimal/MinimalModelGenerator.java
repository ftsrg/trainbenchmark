package hu.bme.mit.trainbenchmark.generator.minimal;

import java.io.FileNotFoundException;
import java.io.IOException;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public abstract class MinimalModelGenerator extends ModelGenerator {

	public MinimalModelGenerator(final ModelSerializer<?> serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

	@Override
	protected void constructModel() throws FileNotFoundException, IOException {
		serializer.beginTransaction();
		buildPatternModel();
		serializer.endTransaction();
	}

	protected abstract void buildPatternModel() throws FileNotFoundException, IOException;

}
