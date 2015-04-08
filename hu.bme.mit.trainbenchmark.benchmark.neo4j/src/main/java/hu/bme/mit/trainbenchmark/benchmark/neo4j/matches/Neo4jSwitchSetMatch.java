package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class Neo4jSwitchSetMatch extends Neo4jMatch implements SwitchSetMatch {

	public Neo4jSwitchSetMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSemaphore() {
		return (Node) match.get(VAR_SEMAPHORE);
	}

	@Override
	public Node getRoute() {
		return (Node) match.get(VAR_ROUTE);
	}

	@Override
	public Node getSwP() {
		return (Node) match.get(VAR_SWP);
	}

	@Override
	public Node getSw() {
		return (Node) match.get(VAR_SW);
	}

}
