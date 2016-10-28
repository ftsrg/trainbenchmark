package hu.bme.mit.trainbenchmark.benchmark.neo4j.queries;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class Neo4jQuery<TNeo4jMatch extends Neo4jMatch> extends ModelQuery<TNeo4jMatch, Neo4jDriver> {

	public Neo4jQuery(final RailwayQuery query, final Neo4jDriver driver) {
		super(query, driver);
	}

}
