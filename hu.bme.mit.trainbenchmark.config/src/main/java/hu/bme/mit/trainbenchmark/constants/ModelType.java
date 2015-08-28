package hu.bme.mit.trainbenchmark.constants;

public enum ModelType {
	RAILWAY("Railway"), //
	SCHEDULE_RANDOM("Schedule-Random"), //
	SCHEDULE_REAL("Schedule-Real"), //
	SCHEDULE_SCALE_FREE("Schedule-Scale-Free"), //
	SCHEDULE_SCALE_FREE_CHAR("Schedule-Scale-Free-Char"), //
	SCHEDULE_SCALE_FREE_HET("Schedule-Scale-Free-Het"), //
	SCHEDULE_SCALE_FREE_HOM("Schedule-Scale-Free-Hom");

	private String type;

	ModelType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
