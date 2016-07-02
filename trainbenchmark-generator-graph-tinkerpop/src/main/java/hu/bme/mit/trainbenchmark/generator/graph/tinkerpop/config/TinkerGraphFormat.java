package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config;

public enum TinkerGraphFormat {
	GRAPHML("GraphML"), //
	GRAPHSON("GraphSON"), //
	GRYO("Gryo");

	private String name;

	TinkerGraphFormat(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
