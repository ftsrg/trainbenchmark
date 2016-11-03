package hu.bme.mit.trainbenchmark.generator.emf.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.emf.EmfSerializer;
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class EmfGeneratorTest extends GeneratorTest {
	
	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final EmfGeneratorConfig gc = new EmfGeneratorConfig(gcb, executionConfig);
		final EmfSerializer serializer = new EmfSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
		generator.generateModel();
	}

}
