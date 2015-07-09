package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.MetricToken;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Iterator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;

public class Neo4jAverageDegreeMetric extends Neo4jConcreteMetric {

	protected double averageDegree;

	protected double sumDegree;

	public Neo4jAverageDegreeMetric(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void calculate(final MetricToken token) {
		beginTransaction();
		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			sumDegree += iterator.next().getDegree();
		}
		averageDegree = sumDegree / token.getNumberOfNodes();
		finishTransaction();

	}

	@Override
	public String getValue() {
		return Double.toString(averageDegree);
	}

}
