package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSensorMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSwitchSensorMatch extends OrientDbMatch implements SwitchSensorMatch {


	public OrientDbSwitchSensorMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getSw() {
		return (Vertex) match.getColumn(VAR_SW);
	}

	@Override
	public Vertex[] toArray() {
		return new Vertex[] { getSw() };
	}
	
}
