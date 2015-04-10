package hu.bme.mit.trainbenchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

public class EMFTransformationRouteSensor extends EMFTransformationAction {

	@Override
	public void transform(final Collection<RailwayElement> routes) {
		for (final RailwayElement railwayElement : routes) {
			final Route route = (Route) railwayElement;
			final EList<Sensor> definedBys = route.getDefinedBy();

			// delete the first edge
			if (definedBys.size() > 0) {
				definedBys.remove(0);
			}
		}
	}

}
