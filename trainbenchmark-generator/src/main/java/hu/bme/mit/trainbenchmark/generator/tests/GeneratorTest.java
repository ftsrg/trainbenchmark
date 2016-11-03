package hu.bme.mit.trainbenchmark.generator.tests;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;

public abstract class GeneratorTest {

	public abstract void generate(final GeneratorConfigBase gcb) throws Exception;

	final int memory = 1000;
	final int size = 1;
	
	@Test
	public void generateBatch() throws Exception {
		final Scenario scenario = Scenario.BATCH;
		final GeneratorConfigBase gcb = new GeneratorConfigBase(memory, memory, scenario, size);
		generate(gcb);
	}

	@Test
	public void generateInject() throws Exception {
		final Scenario scenario = Scenario.INJECT;
		final GeneratorConfigBase gcb = new GeneratorConfigBase(memory, memory, scenario, size);
		generate(gcb);
	}

	@Test
	public void generateRepair() throws Exception {
		final Scenario scenario = Scenario.REPAIR;
		final GeneratorConfigBase gcb = new GeneratorConfigBase(memory, memory, scenario, size);
		generate(gcb);
	}

}
