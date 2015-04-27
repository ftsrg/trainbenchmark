package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;

import java.util.Collection;

public class Neo4jConnectedSegmentsChecker extends Neo4jChecker<Neo4jPosLengthMatch> {

	public Neo4jConnectedSegmentsChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jPosLengthMatch> check() {
		return null;
	}

}
