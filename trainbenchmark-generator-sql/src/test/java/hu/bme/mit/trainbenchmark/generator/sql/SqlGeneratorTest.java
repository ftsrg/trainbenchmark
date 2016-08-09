package hu.bme.mit.trainbenchmark.generator.sql;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

public class SqlGeneratorTest extends GeneratorTest {

	@Override
	public void generate(final GeneratorConfig gc) throws Exception {
		final SqlGeneratorConfigWrapper gcw = new SqlGeneratorConfigWrapper(gc);
		final SqlSerializer serializer = new SqlSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}
	
}
