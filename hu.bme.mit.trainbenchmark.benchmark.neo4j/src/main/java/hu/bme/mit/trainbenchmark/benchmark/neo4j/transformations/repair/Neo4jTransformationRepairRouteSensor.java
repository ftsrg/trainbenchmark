package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.Neo4jConstants.relationshipTypeDefinedBy;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;

import java.util.Collection;

import org.neo4j.graphdb.Node;

public class Neo4jTransformationRepairRouteSensor extends Neo4jTransformationRepair<Neo4jRouteSensorMatch> {

	public Neo4jTransformationRepairRouteSensor() {
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
