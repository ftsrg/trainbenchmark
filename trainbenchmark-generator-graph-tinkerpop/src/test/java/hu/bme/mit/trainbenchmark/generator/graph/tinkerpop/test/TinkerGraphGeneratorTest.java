package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.TinkerGraphSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class TinkerGraphGeneratorTest extends GeneratorTest {

	public void generate(final GeneratorConfigBase gcc) throws Exception {
		final TinkerGraphGeneratorConfig gcw = new TinkerGraphGeneratorConfig(gcc, TinkerGraphFormat.GRAPHML);
		final TinkerGraphSerializer serializer = new TinkerGraphSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}
	
}
