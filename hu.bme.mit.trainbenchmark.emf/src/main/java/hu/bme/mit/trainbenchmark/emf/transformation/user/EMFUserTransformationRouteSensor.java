/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

public class EMFUserTransformationRouteSensor extends EMFUserTransformation {

	public EMFUserTransformationRouteSensor(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<RailwayElement> routes) {
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
