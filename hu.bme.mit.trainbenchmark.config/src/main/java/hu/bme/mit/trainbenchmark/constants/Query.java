package hu.bme.mit.trainbenchmark.constants;

public enum Query {
	POSLENGTH(QueryConstants.POSLENGTH), //
	ROUTESENSOR(QueryConstants.ROUTESENSOR), //
	SEMAPHORENEIGHBOR(QueryConstants.SEMAPHORENEIGHBOR), //
	SWITCHSENSOR(QueryConstants.SWITCHSENSOR), //
	SWITCHSET(QueryConstants.SWITCHSET);

	private String queryString;

	Query(final String queryString) {
		this.queryString = queryString;
	}

	public String toString() {
		return queryString;
	}
}
