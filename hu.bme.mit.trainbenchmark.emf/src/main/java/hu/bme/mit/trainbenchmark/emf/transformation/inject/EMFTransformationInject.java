package hu.bme.mit.trainbenchmark.emf.transformation.inject;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class EMFTransformationUser<T extends RailwayElement> extends EMFTransformation<T> {

	protected EMFDriver driver;

	public EMFTransformationUser(final EMFDriver driver) {
		super();
		this.driver = driver;
	}

}
