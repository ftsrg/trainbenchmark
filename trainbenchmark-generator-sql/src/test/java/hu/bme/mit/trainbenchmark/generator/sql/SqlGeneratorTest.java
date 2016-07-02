package hu.bme.mit.trainbenchmark.generator.sql;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.sql.config.SqlGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorBaseTest;

public class SqlGeneratorTest extends GeneratorBaseTest {

	@Test
	public void generate() throws Exception {
		final SqlGeneratorConfigWrapper gcw = new SqlGeneratorConfigWrapper(gc);
		final SqlSerializer serializer = new SqlSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}
	
}
