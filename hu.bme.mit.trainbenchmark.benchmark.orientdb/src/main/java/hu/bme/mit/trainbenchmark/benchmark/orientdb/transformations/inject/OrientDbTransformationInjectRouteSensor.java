package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeDefinedBy;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.util.Collection;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationUserRouteSensor extends OrientDbTransformationUser {

	public OrientDbTransformationUserRouteSensor(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}
	
	@Override
	public void rhs(final Collection<Vertex> routes) {
		for (final Vertex route : routes) {
			final Iterable<Edge> definedBys = route.getEdges(Direction.OUT, relationshipTypeDefinedBy);
			for (Edge definedBy : definedBys) {
				definedBy.remove();
				break;
			}
		}
	}
	
}
