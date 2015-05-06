package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.OrientDbTransformation;

public abstract class OrientDbTransformationRepair<M extends OrientDbMatch> extends OrientDbTransformation<M> {

	protected OrientDbTransformationRepair(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

}
