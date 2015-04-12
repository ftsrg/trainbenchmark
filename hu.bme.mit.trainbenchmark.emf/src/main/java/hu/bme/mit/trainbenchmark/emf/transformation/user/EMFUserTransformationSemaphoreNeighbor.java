package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.Route;

import java.util.Collection;

public class EMFUserTransformationSemaphoreNeighbor extends EMFUserTransformation {

	public EMFUserTransformationSemaphoreNeighbor(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void transform(final Collection<Object> routes) {
		for (final Object railwayElement : routes) {
			final Route route = (Route) railwayElement;
			route.setEntry(null);
			driver.getContainer().getInvalids().add(route);
		}
	}
}
