package hu.bme.mit.trainbenchmark.generator.sql;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class SqlGeneratorTest extends GeneratorTest {

	@Override
	public void generate(final GeneratorConfigBase gcc) throws Exception {
		final SqlGeneratorConfig gcw = new SqlGeneratorConfig(gcc);
		final SqlSerializer serializer = new SqlSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}
	
}
