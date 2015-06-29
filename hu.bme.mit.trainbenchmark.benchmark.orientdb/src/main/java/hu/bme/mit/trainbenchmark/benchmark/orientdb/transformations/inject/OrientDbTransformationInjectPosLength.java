package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationUserPosLength extends OrientDbTransformationUser {

	public OrientDbTransformationUserPosLength(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<Vertex> segments) {
		for (final Vertex segment : segments) {
			segment.setProperty(LENGTH, 0);
		}
	}
	
}
