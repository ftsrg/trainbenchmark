package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;
import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class Neo4jPosLengthMatch extends Neo4jMatch implements PosLengthMatch {

	public Neo4jPosLengthMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSegment() {
		return (Node) match.get(VAR_SEGMENT);
	}

}
