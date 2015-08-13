package hu.bme.mit.trainbenchmark.generator.rdf;

import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.GeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class RDFGeneratorFactory extends GeneratorFactory {

	public RDFGeneratorFactory(GeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	protected FormatGenerator getRailwayFormatGenerator() {
		return new RDFFormatGenerator(generatorConfig);
	}

	@Override
	protected FormatGenerator getScheduleFormatGenerator() {
		return getRailwayFormatGenerator();
	}

}
