package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeDefinedBy;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbRouteSensorMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairRouteSensor extends OrientDbTransformationRepair<OrientDbRouteSensorMatch> {

	public OrientDbTransformationRepairRouteSensor(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<OrientDbRouteSensorMatch> matches) {
		for (final OrientDbRouteSensorMatch rsm : matches) {
			final Vertex route = rsm.getRoute();
			final Vertex sensor = rsm.getSensor();
			route.addEdge(relationshipTypeDefinedBy, sensor);
		}
	}
	
}
