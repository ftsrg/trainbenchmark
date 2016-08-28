package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;

public abstract class Neo4jTransformation<TNeo4jMatch extends Neo4jMatch> extends ModelTransformation<TNeo4jMatch, Neo4jDriver> {

	public Neo4jTransformation(final Neo4jDriver driver) {
		super(driver);
	}
	
}
