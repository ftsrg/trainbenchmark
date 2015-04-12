package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

public class EMFUserTransformationRouteSensor extends EMFUserTransformation {

	public EMFUserTransformationRouteSensor(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void transform(final Collection<Object> routes) {
		for (final Object railwayElement : routes) {
			final Route route = (Route) railwayElement;
			final EList<Sensor> definedBys = route.getDefinedBy();

			// delete the first edge
			if (definedBys.size() > 0) {
				definedBys.remove(0);
			}
		}
	}

}
