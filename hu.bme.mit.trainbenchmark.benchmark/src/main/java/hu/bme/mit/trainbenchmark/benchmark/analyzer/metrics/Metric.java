package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics;

public abstract class Metric {

	private ConcreteMetric<?> concreteMetric;

	private String identifier;

	protected Metric(String identifier) {
		this.identifier = identifier;
	}

	public ConcreteMetric<?> getConcreteMetric() {
		if (concreteMetric == null) {
			throw new NullPointerException(
					"ConcreteMetric in not initialized!");
		}
		return concreteMetric;
	}

	public void attachConcreteMetric(final ConcreteMetric<?> concreteMetric) {
		this.concreteMetric = concreteMetric;
		this.concreteMetric.setMetricName(identifier);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
