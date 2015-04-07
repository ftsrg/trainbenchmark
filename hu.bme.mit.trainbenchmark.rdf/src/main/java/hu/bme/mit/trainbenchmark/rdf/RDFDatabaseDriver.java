package hu.bme.mit.trainbenchmark.rdf;

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.RDF_TYPE;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;

import java.io.IOException;

public abstract class RDFDatabaseDriver<M, T> extends DatabaseDriver<M, T> {

	protected static final String VAR_SEMAPHORE = "semaphore";
	protected static final String VAR_ROUTE = "route";
	protected static final String VAR_ROUTE2 = "route2";
	protected static final String VAR_SEGMENT = "segment";
	protected static final String VAR_LENGTH = "length";
	protected static final String VAR_SENSOR = "sensor";
	protected static final String VAR_SW = "sw";
	protected static final String VAR_SWP = "swP";
	protected static final String VAR_POSITION = "position";
	protected static final String VAR_CURRENTPOSITION = "currentPosition";

	protected Long determineNewVertexId() throws IOException {
		Long id = 5000L;

		// safety measure to avoid infinite loop in case of a driver bug
		int iterationCount = 1;

		final String askQuery = "PREFIX base: <" + BASE_PREFIX + "> " //
				+ "PREFIX rdf:  <" + RDF_TYPE + "> " //
				+ "ASK { base:" + ID_PREFIX + "%d ?y ?z }";
		while (iterationCount <= 20 && ask(String.format(askQuery, id))) {
			id *= 2;
			iterationCount++;
		}
		if (iterationCount > 20) {
			throw new IOException("Could not generate new unique id.");
		}

		return id;
	}

	protected abstract boolean ask(String askQuery) throws IOException;

}
