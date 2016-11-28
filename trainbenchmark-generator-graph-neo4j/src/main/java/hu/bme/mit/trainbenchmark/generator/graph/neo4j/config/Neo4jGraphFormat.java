package hu.bme.mit.trainbenchmark.generator.graph.neo4j.config;

public enum Neo4jGraphFormat {
	CSV("CSV"), //
	GRAPHML("GraphML"), //
	BINARY("Binary");

	private String name;

	Neo4jGraphFormat(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
