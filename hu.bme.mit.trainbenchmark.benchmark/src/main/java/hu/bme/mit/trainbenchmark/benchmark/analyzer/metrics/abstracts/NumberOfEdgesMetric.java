package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts;


public class NumberOfEdgesMetric extends Metric {

	protected static NumberOfEdgesMetric instance = null;

	protected NumberOfEdgesMetric(String identifier) {
		super(identifier);
	}

	public static Metric instance() {
		if (instance == null) {
			instance = new NumberOfEdgesMetric("NumOfEdges");
		}
		return instance;
	}
}
