package hu.bme.mit.trainbenchmark.constants;

public enum Query {
	POSLENGTH(QueryConstants.POSLENGTH), //
	ROUTESENSOR(QueryConstants.ROUTESENSOR), //
	SEMAPHORENEIGHBOR(QueryConstants.SEMAPHORENEIGHBOR), //
	SWITCHSENSOR(QueryConstants.SWITCHSENSOR), //
	SWITCHSET(QueryConstants.SWITCHSET);

	private String name;

	Query(final String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
