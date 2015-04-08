package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class Neo4jRouteSensorMatch extends Neo4jMatch implements RouteSensorMatch {

	public Neo4jRouteSensorMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getRoute() {
		return (Node) match.get(QueryConstants.VAR_ROUTE);
	}

	@Override
	public Node getSensor() {
		return (Node) match.get(QueryConstants.VAR_SENSOR);
	}

	@Override
	public Node getSwP() {
		return (Node) match.get(QueryConstants.VAR_SWP);
	}

	@Override
	public Node getSw() {
		return (Node) match.get(QueryConstants.VAR_SW);
	}

}
