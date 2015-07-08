package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Iterator;

import org.neo4j.graphdb.Relationship;

public class Neo4jNumberOfEdgesMetric extends Neo4jConcreteMetric {

	protected int numberOfEdges;

	public Neo4jNumberOfEdgesMetric(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void calculate() {
		beginTransaction();
		Iterable<Relationship> relationships = graphOperations
				.getAllRelationships();
		Iterator<Relationship> iterator = relationships.iterator();
		numberOfEdges = 0;
		while (iterator.hasNext()) {
			iterator.next();
			numberOfEdges++;
		}
		finishTransaction();
	}

	@Override
	public String getValue() {
		return Integer.toString(numberOfEdges);
	}

}
