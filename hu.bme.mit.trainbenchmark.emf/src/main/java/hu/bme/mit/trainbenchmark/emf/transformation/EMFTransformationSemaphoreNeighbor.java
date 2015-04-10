package hu.bme.mit.trainbenchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Route;

import java.util.Collection;

public class EMFTransformationSemaphoreNeighbor extends EMFTransformationAction {

	@Override
	public void transform(final Collection<RailwayElement> routes) {
		for (final RailwayElement railwayElement : routes) {
			final Route route = (Route) railwayElement;
			route.setEntry(null);
			driver.getContainer().getInvalids().add(route);
		}
	}
}
