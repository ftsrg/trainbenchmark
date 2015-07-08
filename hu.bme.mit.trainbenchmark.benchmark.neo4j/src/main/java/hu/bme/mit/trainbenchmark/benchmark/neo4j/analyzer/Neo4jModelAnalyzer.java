package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jNumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jNumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

public class Neo4jModelAnalyzer extends ModelAnalyzer<Neo4jDriver> {

	@Override
	public void initializeMetrics(Neo4jDriver driver) {
		NumberOfNodesMetric.instance().attachConcreteMetric(
				new Neo4jNumberOfNodesMetric(driver));
		NumberOfEdgesMetric.instance().attachConcreteMetric(
				new Neo4jNumberOfEdgesMetric(driver));

	}
}
