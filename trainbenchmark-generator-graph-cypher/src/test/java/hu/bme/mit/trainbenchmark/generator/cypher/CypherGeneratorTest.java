package hu.bme.mit.trainbenchmark.generator.cypher;

import com.google.common.collect.ImmutableList;
import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherFormat;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;

import java.util.List;

public class CypherGeneratorTest extends GeneratorTest {

	protected final List<CypherFormat> formats = ImmutableList.of(
		CypherFormat.BASIC, //
		CypherFormat.GRAPHFLOW //
	);

	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		for (CypherFormat format : formats) {
			final CypherGeneratorConfig gc = new CypherGeneratorConfigBuilder().setConfigBase(gcb).setGraphFormat(format).createConfig();
			final CypherSerializer serializer = new CypherSerializer(gc);
			final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
			generator.generateModel();
		}
	}

}
