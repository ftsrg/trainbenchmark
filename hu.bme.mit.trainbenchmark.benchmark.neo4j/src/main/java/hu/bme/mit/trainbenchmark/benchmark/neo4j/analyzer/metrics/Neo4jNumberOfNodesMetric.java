package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.MetricToken;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Iterator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;

public class Neo4jNumberOfNodesMetric extends Neo4jConcreteMetric {

	protected int numberOfNodes;

	public Neo4jNumberOfNodesMetric(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void calculate(final MetricToken token) {
		beginTransaction();
		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();
		numberOfNodes = 0;
		while (iterator.hasNext()) {
			iterator.next();
			numberOfNodes++;
		}
		finishTransaction();
		token.setNumberOfNodes(numberOfNodes);
	}

	@Override
	public String getValue() {
		return Integer.toString(numberOfNodes);
	}
}