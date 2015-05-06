package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSwitchSetMatch extends OrientDbMatch implements SwitchSetMatch {

	public OrientDbSwitchSetMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getSemaphore() {
		return (Vertex) match.getColumn(VAR_SEMAPHORE);
	}

	@Override
	public Vertex getRoute() {
		return (Vertex) match.getColumn(VAR_ROUTE);
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
		return new Vertex[] { getSemaphore(), getRoute(), getSwP(), getSw() };
	}
	
}
