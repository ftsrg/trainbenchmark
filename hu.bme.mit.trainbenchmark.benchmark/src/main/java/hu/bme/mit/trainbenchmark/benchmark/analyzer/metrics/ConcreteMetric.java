package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import eu.mondo.sam.core.metrics.BenchmarkMetric;

public abstract class ConcreteMetric<D extends Driver<?>> extends
		BenchmarkMetric {

	protected D driver;

	public ConcreteMetric(D driver) {
		this.driver = driver;
	}

	public ConcreteMetric(D driver, String identifier) {
		super(identifier);
		this.driver = driver;
	}

	public abstract void calculate();

	public D getDriver() {
		return driver;
	}

	public void setDriver(D driver) {
		this.driver = driver;
	}
}
