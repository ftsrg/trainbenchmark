package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config;

public enum GraphFormat {
	GRAPHML("GraphML"), //
	GRAPHSON("GraphSON"), //
	GRYO("Gryo");

	private String name;

	GraphFormat(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
