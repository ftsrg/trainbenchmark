package hu.bme.mit.trainbenchmark.neo4j.config;

public enum Neo4jGraphFormat {
	CSV("CSV"), //
	GRAPHML("GraphML"), //
	;

	private String name;

	Neo4jGraphFormat(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
