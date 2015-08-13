package hu.bme.mit.trainbenchmark.generator.emf;

import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.GeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class EMFGeneratorFactory extends GeneratorFactory {

	public EMFGeneratorFactory(GeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	protected FormatGenerator getRailwayFormatGenerator() {
		return new EMFRailwayGenerator(generatorConfig);
	}

	@Override
	protected FormatGenerator getScheduleFormatGenerator() {
		return getRailwayFormatGenerator();
	}

}
