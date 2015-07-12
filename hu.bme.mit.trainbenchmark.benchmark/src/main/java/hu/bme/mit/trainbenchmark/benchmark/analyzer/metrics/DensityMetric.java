package hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics;

import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

public class DensityMetric extends Metric {

	public DensityMetric(String identifier, EdgeDirection direction) {
		super(identifier, direction);
	}

	@Override
	public void calculate() {
		double numOfNodes = analyzer.getNumberOfNodes(withOutgoingDegree);
		metricValue = analyzer.getNumberOfEdges() / numOfNodes;
		metricValue /= (numOfNodes - 1);
	}

}
