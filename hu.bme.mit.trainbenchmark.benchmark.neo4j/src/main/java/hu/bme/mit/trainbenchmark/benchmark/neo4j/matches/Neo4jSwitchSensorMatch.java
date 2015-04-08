package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSensorMatch;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class Neo4jSwitchSensorMatch extends Neo4jMatch implements SwitchSensorMatch {

	public Neo4jSwitchSensorMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSw() {
		return (Node) match.get(VAR_SW);
	}

}
