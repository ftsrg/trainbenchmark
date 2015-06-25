package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeEntry;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.util.Collection;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationUserSemaphoreNeighbor extends OrientDbTransformationUser{

	public OrientDbTransformationUserSemaphoreNeighbor(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<Vertex> routes) {
		for (final Vertex route : routes) {
			final Iterable<Edge> entries = route.getEdges(Direction.BOTH, relationshipTypeEntry);
			for (final Edge entry : entries) {
				entry.remove();
			}
		}
	}
	
}
