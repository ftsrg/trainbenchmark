package hu.bme.mit.trainbenchmark.neo4j.config;

public enum Neo4jDeployment {
	EMBEDDED("embedded"),
	IN_MEMORY("in-memory");

	private String name;

	Neo4jDeployment(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}


}
