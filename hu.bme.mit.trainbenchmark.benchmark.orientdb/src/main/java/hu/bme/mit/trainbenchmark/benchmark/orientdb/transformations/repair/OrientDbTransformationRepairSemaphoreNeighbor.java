package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeEntry;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSemaphoreNeighborMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairSemaphoreNeighbor extends OrientDbTransformationRepair<OrientDbSemaphoreNeighborMatch> {

	public OrientDbTransformationRepairSemaphoreNeighbor(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<OrientDbSemaphoreNeighborMatch> matches) {
		for (OrientDbSemaphoreNeighborMatch snm : matches) {
			final Vertex semaphore = snm.getSemaphore();
			final Vertex route2 = snm.getRoute2();
			route2.addEdge(relationshipTypeEntry, semaphore);
		}
	}
	
}
