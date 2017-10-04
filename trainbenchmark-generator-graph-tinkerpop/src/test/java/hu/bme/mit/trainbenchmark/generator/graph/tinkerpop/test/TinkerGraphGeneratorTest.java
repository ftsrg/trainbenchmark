package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.test;

import com.google.common.collect.ImmutableList;
import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.TinkerGraphSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphFormat;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerGraphGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

import java.util.List;

public class TinkerGraphGeneratorTest extends GeneratorTest {

	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final TinkerGraphGeneratorConfig gc = new TinkerGraphGeneratorConfigBuilder().setConfigBase(gcb)
			.setGraphFormat(TinkerGraphFormat.GRAPHML).createConfig();
		final TinkerGraphSerializer serializer = new TinkerGraphSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
		generator.generateModel();
	}

}
