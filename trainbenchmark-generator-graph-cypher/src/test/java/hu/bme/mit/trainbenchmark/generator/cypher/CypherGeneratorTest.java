package hu.bme.mit.trainbenchmark.generator.sql;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.cypher.CypherSerializer;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class CypherGeneratorTest extends GeneratorTest {

	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final CypherGeneratorConfig gc = new CypherGeneratorConfigBuilder().setConfigBase(gcb).createConfig();
		final CypherSerializer serializer = new CypherSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
		generator.generateModel();
	}

}
