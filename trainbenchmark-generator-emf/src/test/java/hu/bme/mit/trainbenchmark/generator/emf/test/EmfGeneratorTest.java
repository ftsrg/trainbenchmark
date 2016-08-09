package hu.bme.mit.trainbenchmark.generator.emf.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.emf.EmfSerializer;
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class EmfGeneratorTest extends GeneratorTest {
	
	@Override
	public void generate(final GeneratorConfig gc) throws Exception {
		final EmfGeneratorConfigWrapper gcw = new EmfGeneratorConfigWrapper(gc);
		final EmfSerializer serializer = new EmfSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

}
