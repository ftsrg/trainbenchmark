package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config;

public enum TinkerGraphEngine {
	COREAPI("Core_API"), CYPHER("Cypher");

	private String name;

	TinkerGraphEngine(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
