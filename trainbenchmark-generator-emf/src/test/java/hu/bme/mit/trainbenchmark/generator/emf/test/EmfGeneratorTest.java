package hu.bme.mit.trainbenchmark.generator.emf.test;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.emf.EmfSerializer;
import hu.bme.mit.trainbenchmark.generator.emf.config.EmfGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorBaseTest;

public class EmfGeneratorTest extends GeneratorBaseTest {
	
	@Test
	public void generate() throws Exception {
		final EmfGeneratorConfigWrapper gcw = new EmfGeneratorConfigWrapper(gc);
		final EmfSerializer serializer = new EmfSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

}
