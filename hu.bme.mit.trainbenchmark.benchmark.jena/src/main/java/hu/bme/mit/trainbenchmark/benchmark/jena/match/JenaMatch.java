package hu.bme.mit.trainbenchmark.benchmark.jena.match;

import hu.bme.mit.trainbenchmark.constants.Query;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

public abstract class JenaMatch {

	protected QuerySolution qs;

	public JenaMatch(final QuerySolution qs) {
		this.qs = qs;
	}

	public abstract Resource[] toArray();

	public static JenaMatch createMatch(final Query query, final QuerySolution qs) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new JenaConnectedSegmentsMatch(qs);
		case POSLENGTH:
			return new JenaPosLengthMatch(qs);
		case ROUTESENSOR:
			return new JenaRouteSensorMatch(qs);
		case SEMAPHORENEIGHBOR:
			return new JenaSemaphoreNeighborMatch(qs);
		case SWITCHSENSOR:
			return new JenaSwitchSensorMatch(qs);
		case SWITCHSET:
			return new JenaSwitchSetMatch(qs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

}
