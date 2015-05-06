package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.OrientDbTransformation;

import com.tinkerpop.blueprints.Vertex;

public abstract class OrientDbTransformationUser extends OrientDbTransformation<Vertex> {

	protected OrientDbTransformationUser(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}
	
}
