package hu.bme.mit.trainbenchmark.benchmark.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

public abstract class ModelAnalyzer<D extends Driver<?>> extends Analyzer<D> {

	@Override
	public void attachMetrics() {
		metrics.add(NumberOfNodesMetric.instance().getConcreteMetric());
		metrics.add(NumberOfEdgesMetric.instance().getConcreteMetric());
	}

}
