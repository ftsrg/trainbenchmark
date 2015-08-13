package hu.bme.mit.trainbenchmark.generator.sql;

import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.GeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class SQLGeneratorFactory extends GeneratorFactory {

	public SQLGeneratorFactory(GeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	protected FormatGenerator getRailwayFormatGenerator() {
		return new SQLFormatGenerator(generatorConfig);
	}

	@Override
	protected FormatGenerator getScheduleFormatGenerator() {
		return getRailwayFormatGenerator();
	}

}
