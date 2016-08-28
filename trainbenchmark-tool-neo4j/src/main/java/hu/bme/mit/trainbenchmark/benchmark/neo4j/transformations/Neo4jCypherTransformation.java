package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;

public abstract class Neo4jCypherTransformation<TNeo4jMatch extends Neo4jMatch> extends Neo4jTransformation<TNeo4jMatch> {

	public Neo4jCypherTransformation(final Neo4jDriver driver) {
		super(driver);
	}

}
