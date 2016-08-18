package hu.bme.mit.trainbenchmark.generator.emf.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigCore;
import hu.bme.mit.trainbenchmark.generator.emf.EmfSerializer;
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class EmfGeneratorTest extends GeneratorTest {
	
	@Override
	public void generate(final GeneratorConfigCore gcc) throws Exception {
		final EmfGeneratorConfigWrapper gcw = new EmfGeneratorConfigWrapper(gcc);
		final EmfSerializer serializer = new EmfSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

}
