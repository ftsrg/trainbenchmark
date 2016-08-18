package hu.bme.mit.trainbenchmark.generator.rdf.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigCore;
import hu.bme.mit.trainbenchmark.generator.rdf.RdfSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfWithInfererredGeneratorTest extends GeneratorTest {
	
	@Override
	public void generate(final GeneratorConfigCore gc) throws Exception {
		final RdfGeneratorConfigWrapper gcw = new RdfGeneratorConfigWrapper(gc, true, RdfFormat.TURTLE);
		final RdfSerializer serializer = new RdfSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

}
