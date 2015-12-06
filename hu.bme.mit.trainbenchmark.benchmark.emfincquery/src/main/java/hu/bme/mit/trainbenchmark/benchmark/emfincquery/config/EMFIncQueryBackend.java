package hu.bme.mit.trainbenchmark.benchmark.emfincquery.config;

public enum EMFIncQueryBackend {
	INCREMENTAL("Incremental"), LOCALSEARCH("Local_Search");

	private String name;

	EMFIncQueryBackend(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	
}
