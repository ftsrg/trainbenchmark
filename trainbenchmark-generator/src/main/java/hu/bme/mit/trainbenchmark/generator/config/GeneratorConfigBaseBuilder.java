package hu.bme.mit.trainbenchmark.generator.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.constants.Scenario;

public final class GeneratorConfigBaseBuilder {
	private Scenario scenario;
	private Integer size;

	public GeneratorConfigBaseBuilder setScenario(Scenario scenario) {
		this.scenario = scenario;
		return this;
	}

	public GeneratorConfigBaseBuilder setSize(int size) {
		this.size = size;
		return this;
	}

	public GeneratorConfigBase createGeneratorConfigBase() {
		Preconditions.checkNotNull(scenario);
		Preconditions.checkNotNull(size);
		return new GeneratorConfigBase(scenario, size);
	}

}
