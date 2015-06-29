package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.OrientDbTransformation;

import com.tinkerpop.blueprints.Vertex;

public abstract class OrientDbTransformationInject extends OrientDbTransformation<Vertex> {

	protected OrientDbTransformationInject(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}
	
}
