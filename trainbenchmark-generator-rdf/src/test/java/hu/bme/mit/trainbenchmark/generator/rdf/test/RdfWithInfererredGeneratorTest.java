package hu.bme.mit.trainbenchmark.generator.rdf.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.rdf.RdfSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfWithInfererredGeneratorTest extends GeneratorTest {
	
	@Override
	public void generate(final GeneratorConfigBase gcc) throws Exception {
		final RdfGeneratorConfig gcw = new RdfGeneratorConfig(gcc, true, RdfFormat.TURTLE);
		final RdfSerializer serializer = new RdfSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

}
