package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics;

public class NumberOfEdgesMetric extends Metric {

	protected NumberOfEdgesMetric(String identifier) {
		super(identifier);
	}

	public static Metric instance() {
		if (instance == null) {
			return new NumberOfEdgesMetric("NumOfEdges");
		}
		return instance;
	}
}
