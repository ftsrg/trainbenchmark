package hu.bme.mit.trainbenchmark.benchmark.viatra.config;

public enum ViatraBackend {
	INCREMENTAL("Incremental"),
	LOCAL_SEARCH("Local Search");

	private String name;

	ViatraBackend(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
