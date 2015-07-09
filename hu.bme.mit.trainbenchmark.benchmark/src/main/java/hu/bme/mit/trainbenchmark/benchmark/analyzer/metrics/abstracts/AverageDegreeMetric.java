package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts;


public class AverageDegreeMetric extends Metric {

	protected static AverageDegreeMetric instance = null;

	protected AverageDegreeMetric(String identifier) {
		super(identifier);
	}

	public static Metric instance() {
		if (instance == null) {
			instance = new AverageDegreeMetric("AvgDegree");
		}
		return instance;
	}
}
