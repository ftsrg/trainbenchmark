package hu.bme.mit.trainbenchmark.generator.rdf.test;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.rdf.RdfSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

@RunWith(Parameterized.class)
public class RdfGeneratorTest extends GeneratorTest {

	@Parameters(name="inferencing={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(false, true);
	}

	@Parameter
	public boolean inferencing;

	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final RdfGeneratorConfig gc = new RdfGeneratorConfigBuilder().setConfigBase(gcb).setInferencing(inferencing)
				.setFormat(RdfFormat.TURTLE).createConfig();
		final RdfSerializer serializer = new RdfSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
		generator.generateModel();
	}

}
