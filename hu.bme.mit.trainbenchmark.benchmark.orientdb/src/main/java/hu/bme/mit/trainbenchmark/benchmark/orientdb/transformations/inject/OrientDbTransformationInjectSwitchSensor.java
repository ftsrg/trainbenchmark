package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.util.Collection;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationInjectSwitchSensor extends OrientDbTransformationInject {

	public OrientDbTransformationInjectSwitchSensor(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}
	
	@Override
	public void rhs(final Collection<Vertex> switches) {
		for (final Vertex sw : switches) {
			final Iterable<Edge> sensors = sw.getEdges(Direction.OUT, relationshipTypeSensor);
			for (final Edge sensor : sensors) {
				sensor.remove();
			}
		}
	}
	
}
