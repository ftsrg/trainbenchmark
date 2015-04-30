package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class EMFUserTransformation<T extends RailwayElement> extends EMFTransformation<T> {

	protected EMFDriver driver;

	public EMFUserTransformation(final EMFDriver driver) {
		super();
		this.driver = driver;
	}

}
