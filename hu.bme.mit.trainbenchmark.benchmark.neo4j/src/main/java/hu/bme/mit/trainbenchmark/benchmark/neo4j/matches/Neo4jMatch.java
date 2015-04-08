package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import java.util.Map;

public abstract class Neo4jMatch {

	protected Map<String, Object> match;

	public Neo4jMatch(final Map<String, Object> match) {
		this.match = match;
	}

}
