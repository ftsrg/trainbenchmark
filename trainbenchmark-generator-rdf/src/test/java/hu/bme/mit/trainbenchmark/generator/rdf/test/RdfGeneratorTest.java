package hu.bme.mit.trainbenchmark.generator.rdf.test;

import java.util.List;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.rdf.RdfSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfGeneratorTest extends GeneratorTest {

	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final RdfGeneratorConfigBuilder builder = new RdfGeneratorConfigBuilder().setConfigBase(gcb)
				.setFormat(RdfFormat.TURTLE);

		final List<Boolean> inferredConfigs = ImmutableList.of(true, false);
		for (Boolean inferredConfig : inferredConfigs) {
			final RdfGeneratorConfig gc = builder.setInferred(inferredConfig).createConfig();
			final RdfSerializer serializer = new RdfSerializer(gc);
			final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
			generator.generateModel();
		}
	}

}
