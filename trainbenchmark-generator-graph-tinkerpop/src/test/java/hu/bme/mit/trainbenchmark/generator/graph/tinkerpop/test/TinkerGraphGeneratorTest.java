package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.test;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.TinkerGraphSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorBaseTest;

public class TinkerGraphGeneratorTest extends GeneratorBaseTest {

	@Test
	public void generate() throws Exception {
		final TinkerGraphGeneratorConfigWrapper gcw = new TinkerGraphGeneratorConfigWrapper(gc, TinkerGraphFormat.GRAPHML);
		final TinkerGraphSerializer serializer = new TinkerGraphSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}
	
}
