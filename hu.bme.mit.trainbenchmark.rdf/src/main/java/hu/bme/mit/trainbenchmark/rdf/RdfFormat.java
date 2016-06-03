package hu.bme.mit.trainbenchmark.rdf;

public enum RdfFormat {
	TURTLE("ttl"), NTRIPLES("nt");

	private String extension;

	RdfFormat(final String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}
	
}
