package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;
import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbPosLengthMatch extends OrientDbMatch implements PosLengthMatch {

	public OrientDbPosLengthMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getSegment() {
		return (Vertex) match.getColumn(VAR_SEGMENT);
	}

	@Override
	public Vertex[] toArray() {
		return new Vertex[] { getSegment() };
	}
	
}
