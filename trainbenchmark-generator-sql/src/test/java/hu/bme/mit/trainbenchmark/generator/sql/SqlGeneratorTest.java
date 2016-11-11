package hu.bme.mit.trainbenchmark.generator.sql;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class SqlGeneratorTest extends GeneratorTest {

	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		final SqlGeneratorConfig gc = new SqlGeneratorConfigBuilder().setConfigBase(gcb).createConfig();
		final SqlSerializer serializer = new SqlSerializer(gc);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
		generator.generateModel();
	}

}
