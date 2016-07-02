package hu.bme.mit.trainbenchmark.generator.tests;

import org.junit.BeforeClass;

import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class GeneratorBaseTest {

	protected static GeneratorConfig gc;

	@BeforeClass
	public static void init() {
		final String xms = "1G";
		final String xmx = "1G";
		final Scenario scenario = Scenario.REPAIR;
		final int size = 1;
		
		gc = new GeneratorConfig(xms, xmx, scenario, size);
	}
	
}
