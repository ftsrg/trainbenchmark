package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbRouteSensorMatch extends OrientDbMatch implements RouteSensorMatch {

	public OrientDbRouteSensorMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getRoute() {
		return (Vertex) match.getColumn(VAR_ROUTE);
	}

	@Override
	public Vertex getSensor() {
		return (Vertex) match.getColumn(VAR_SENSOR);
	}

	@Override
	public Vertex getSwP() {
		return (Vertex) match.getColumn(VAR_SWP.toLowerCase());
	}

	@Override
	public Vertex getSw() {
		return (Vertex) match.getColumn(VAR_SW);
	}

	@Override
	public Vertex[] toArray() {
		return new Vertex[] { getRoute(), getSensor(), getSwP(), getSw() };
	}
	
}
