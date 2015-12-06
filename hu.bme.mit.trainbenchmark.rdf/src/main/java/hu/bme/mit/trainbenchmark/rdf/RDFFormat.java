package hu.bme.mit.trainbenchmark.rdf;

public enum RDFFormat {
	TURTLE("ttl"), NTRIPLES("nt");

	private String extension;

	RDFFormat(final String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}
	
}
