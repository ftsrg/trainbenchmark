package hu.bme.mit.trainbenchmark.generator.rdf.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.rdf.RdfSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfWithoutInfererredGeneratorTest extends GeneratorTest {
	
	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final RdfGeneratorConfig gc = new RdfGeneratorConfig(gcb, executionConfig, false, RdfFormat.TURTLE);
		final RdfSerializer serializer = new RdfSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
		generator.generateModel();
	}

}
