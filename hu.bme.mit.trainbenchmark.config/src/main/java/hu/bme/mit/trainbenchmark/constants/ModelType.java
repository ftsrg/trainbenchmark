package hu.bme.mit.trainbenchmark.constants;

public enum ModelType {
	RAILWAY("Railway"), 
	SCHEDULE_REAL("Schedule-Real");
	
	private String type;
	
	ModelType(final String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
