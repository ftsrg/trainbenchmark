package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;
import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class Neo4jSemaphoreNeighborMatch extends Neo4jMatch implements SemaphoreNeighborMatch {

	public Neo4jSemaphoreNeighborMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSemaphore() {
		return (Node) match.get(VAR_SEMAPHORE);
	}

	@Override
	public Node getRoute1() {
		return (Node) match.get(VAR_ROUTE1);
	}

	@Override
	public Node getRoute2() {
		return (Node) match.get(VAR_ROUTE2);
	}

	@Override
	public Node getSensor1() {
		return (Node) match.get(VAR_SENSOR1);
	}

	@Override
	public Node getSensor2() {
		return (Node) match.get(VAR_SENSOR2);
	}

	@Override
	public Node getTe1() {
		return (Node) match.get(VAR_TE1);
	}

	@Override
	public Node getTe2() {
		return (Node) match.get(VAR_TE2);
	}

}
