package hu.bme.mit.trainbenchmark.benchmark.neo4j.config;

public enum Neo4jEngine {
	CORE_API("Core API"), CYPHER("Cypher");

	private String name;

	Neo4jEngine(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
