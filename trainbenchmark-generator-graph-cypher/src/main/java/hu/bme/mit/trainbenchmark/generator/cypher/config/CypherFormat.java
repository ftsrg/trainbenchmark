package hu.bme.mit.trainbenchmark.generator.cypher.config;

public enum CypherFormat {
	BASIC("Basic"), //
	GRAPHFLOW("Graphflow"), //
	;

	private String name;

	CypherFormat(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
