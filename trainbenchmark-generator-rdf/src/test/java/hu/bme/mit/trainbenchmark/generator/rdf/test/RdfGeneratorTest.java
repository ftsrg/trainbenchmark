package hu.bme.mit.trainbenchmark.generator.rdf.test;

import com.google.common.collect.ImmutableList;
import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.rdf.RdfSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

import java.util.List;

public class RdfGeneratorTest extends GeneratorTest {

	protected final List<Boolean> inferredConfigs = ImmutableList.of(true, false);
	protected final List<RdfFormat> formats = ImmutableList.of(
		RdfFormat.NTRIPLES, //
		RdfFormat.TURTLE //
	);


	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final RdfGeneratorConfigBuilder builder = new RdfGeneratorConfigBuilder().setConfigBase(gcb);

		for (final RdfFormat format : formats) {
			builder.setFormat(format);
			for (Boolean inferredConfig : inferredConfigs) {
				final RdfGeneratorConfig gc = builder.setInferred(inferredConfig).createConfig();
				final RdfSerializer serializer = new RdfSerializer(gc);
				final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
				generator.generateModel();
			}
		}
	}

}
