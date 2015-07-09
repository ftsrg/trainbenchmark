package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.AverageDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.abstracts.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jAverageDegreeMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jNumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics.Neo4jNumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

public class Neo4jModelAnalyzer extends ModelAnalyzer<Neo4jDriver> {

	@Override
	public void attachConcreteMetrics(Neo4jDriver driver) {
		NumberOfNodesMetric.instance().attachConcreteMetric(
				new Neo4jNumberOfNodesMetric(driver));
		NumberOfEdgesMetric.instance().attachConcreteMetric(
				new Neo4jNumberOfEdgesMetric(driver));
		AverageDegreeMetric.instance().attachConcreteMetric(
				new Neo4jAverageDegreeMetric(driver));

	}
}
