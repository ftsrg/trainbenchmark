package hu.bme.mit.trainbenchmark.generator.tests;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBaseBuilder;
import org.junit.Test;

import hu.bme.mit.trainbenchmark.config.ExecutionConfig;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;

public abstract class GeneratorTest {

	public abstract void generate(final GeneratorConfigBase gcb) throws Exception;

	protected final int size = 1;
	protected final ExecutionConfig executionConfig = ExecutionConfig.defaultExecutionConfig();

	@Test
	public void generateBatch() throws Exception {
		final Scenario scenario = Scenario.BATCH;
		final GeneratorConfigBase gcb = new GeneratorConfigBaseBuilder().setScenario(scenario).setSize(size).createGeneratorConfigBase();
		generate(gcb);
	}

	@Test
	public void generateInject() throws Exception {
		final Scenario scenario = Scenario.INJECT;
		final GeneratorConfigBase gcb = new GeneratorConfigBaseBuilder().setScenario(scenario).setSize(size).createGeneratorConfigBase();
		generate(gcb);
	}

	@Test
	public void generateRepair() throws Exception {
		final Scenario scenario = Scenario.REPAIR;
		final GeneratorConfigBase gcb = new GeneratorConfigBaseBuilder().setScenario(scenario).setSize(size).createGeneratorConfigBase();
		generate(gcb);
	}

}
