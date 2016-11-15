package hu.bme.mit.trainbenchmark.constants;

public enum ExecutionPhase {
	READ("Read"), //
	CHECK("Check"), //
	TRANSFORMATION("Transformation"), //
	RECHECK("Recheck"), //
	;

	private String name;

	ExecutionPhase(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
