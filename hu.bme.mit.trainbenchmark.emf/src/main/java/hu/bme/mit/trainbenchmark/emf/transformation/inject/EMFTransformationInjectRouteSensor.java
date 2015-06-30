/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.emf.transformation.inject;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

public class EMFTransformationInjectRouteSensor extends EMFTransformationInject<Route> {

	public EMFTransformationInjectRouteSensor(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Route> routes) {
		for (final Route route : routes) {
			final EList<Sensor> definedBys = route.getDefinedBy();

			// delete the first edge
			if (definedBys.size() > 0) {
				definedBys.remove(0);
			}
		}
	}

}
