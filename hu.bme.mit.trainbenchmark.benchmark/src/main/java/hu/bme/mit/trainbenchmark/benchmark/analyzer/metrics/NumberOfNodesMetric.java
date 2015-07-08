package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics;

public class NumberOfNodesMetric extends Metric {

	protected NumberOfNodesMetric(String identifier) {
		super(identifier);
	}

	public static Metric instance() {
		if (instance == null) {
			return new NumberOfNodesMetric("NumOfNodes");
		}
		return instance;
	}

}
