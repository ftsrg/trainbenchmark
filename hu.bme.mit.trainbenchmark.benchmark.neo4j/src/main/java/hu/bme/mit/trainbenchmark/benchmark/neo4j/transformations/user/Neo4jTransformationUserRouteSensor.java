package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.Neo4jConstants.relationshipTypeDefinedBy;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;

import java.util.Collection;

import org.neo4j.graphdb.Node;

public class Neo4jTransformationUserRouteSensor extends Neo4jTransformationUser<Neo4jRouteSensorMatch> {

	public Neo4jTransformationUserRouteSensor() {
		super();
	}

	@Override
	public void rhs(final Collection<Neo4jRouteSensorMatch> matches) {
		for (final Neo4jRouteSensorMatch rsm : matches) {
			final Node route = rsm.getRoute();
			final Node sensor = rsm.getSensor();
			route.createRelationshipTo(sensor, relationshipTypeDefinedBy);
		}
	}

}
