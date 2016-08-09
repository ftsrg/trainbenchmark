package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.TinkerGraphSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorBaseTest;

public class TinkerGraphGeneratorTest extends GeneratorBaseTest {

	public void generate(final GeneratorConfig gc) throws Exception {
		final TinkerGraphGeneratorConfigWrapper gcw = new TinkerGraphGeneratorConfigWrapper(gc, TinkerGraphFormat.GRAPHML);
		final TinkerGraphSerializer serializer = new TinkerGraphSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}
	
}
