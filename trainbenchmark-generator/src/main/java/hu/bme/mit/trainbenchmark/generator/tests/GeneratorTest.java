package hu.bme.mit.trainbenchmark.generator.tests;

import org.junit.Test;

import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigCore;

public abstract class GeneratorTest {

	public abstract void generate(final GeneratorConfigCore gcc) throws Exception;

	@Test
	public void generateBatch() throws Exception {
		final String xms = "1G";
		final String xmx = "1G";
		final Scenario scenario = Scenario.BATCH;
		final int size = 1;

		final GeneratorConfigCore gcc = new GeneratorConfigCore(xms, xmx, scenario, size);
		generate(gcc);
	}

	@Test
	public void generateInject() throws Exception {
		final String xms = "1G";
		final String xmx = "1G";
		final Scenario scenario = Scenario.INJECT;
		final int size = 1;

		final GeneratorConfigCore gcc = new GeneratorConfigCore(xms, xmx, scenario, size);
		generate(gcc);
	}

	@Test
	public void generateRepair() throws Exception {
		final String xms = "1G";
		final String xmx = "1G";
		final Scenario scenario = Scenario.REPAIR;
		final int size = 1;

		final GeneratorConfigCore gcc = new GeneratorConfigCore(xms, xmx, scenario, size);
		generate(gcc);
	}

}
