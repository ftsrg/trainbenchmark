package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Iterator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;

public class Neo4jNumberOfNodesMetric extends Neo4jConcreteMetric {

	protected int numOfNodes;

	public Neo4jNumberOfNodesMetric(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void calculate() {
		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();
		numOfNodes = 0;
		while (iterator.hasNext()) {
			iterator.next();
			numOfNodes++;
		}
	}

	@Override
	public String getValue() {
		return Integer.toString(numOfNodes);
	}
}
