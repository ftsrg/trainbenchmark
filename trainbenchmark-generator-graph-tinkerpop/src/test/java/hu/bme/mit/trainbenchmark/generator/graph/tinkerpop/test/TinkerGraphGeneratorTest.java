package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.TinkerGraphSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class TinkerGraphGeneratorTest extends GeneratorTest {

	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final TinkerGraphGeneratorConfig gc = new TinkerGraphGeneratorConfig(gcb, TinkerGraphFormat.GRAPHML);
		final TinkerGraphSerializer serializer = new TinkerGraphSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
		generator.generateModel();
	}
	
}
