package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts;


public class NumberOfNodesMetric extends Metric {

	protected static NumberOfNodesMetric instance = null;

	protected NumberOfNodesMetric(String identifier) {
		super(identifier);
	}

	public static Metric instance() {
		if (instance == null) {
			instance = new NumberOfNodesMetric("NumOfNodes");
		}
		return instance;
	}

}
