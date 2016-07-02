package hu.bme.mit.trainbenchmark.generator.rdf.test;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.rdf.RdfSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorBaseTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfGeneratorTest extends GeneratorBaseTest {
	
	@Test
	public void generateWithInferred() throws Exception {
		final RdfGeneratorConfigWrapper gcw = new RdfGeneratorConfigWrapper(gc, true, RdfFormat.TURTLE);
		final RdfSerializer serializer = new RdfSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

	@Test
	public void generateWithoutInferred() throws Exception {
		final RdfGeneratorConfigWrapper gcw = new RdfGeneratorConfigWrapper(gc, false, RdfFormat.TURTLE);
		final RdfSerializer serializer = new RdfSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

}
