package hu.bme.mit.trainbenchmark.benchmark.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.ConcreteMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.MetricToken;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Analyzer<D extends Driver<?>> {

	protected ArrayList<ConcreteMetric<?>> metrics;

	protected HashMap<String, String> results;

	public void calculateAll() {
		MetricToken token = new MetricToken();
		for (ConcreteMetric<?> m : metrics) {
			m.calculate(token);
		}
	}

	public ArrayList<ConcreteMetric<?>> getMetrics() {
		return metrics;
	}

	public void setMetrics(ArrayList<ConcreteMetric<?>> metrics) {
		this.metrics = metrics;
	}

	public abstract void collectConcreteMetrics();

	public abstract void attachConcreteMetrics(D driver);
}
