package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config;

public enum TinkerGraphEngine {
	CORE_API("Core API"), GREMLIN("Gremlin");

	private String name;

	TinkerGraphEngine(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
