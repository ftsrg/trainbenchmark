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
import hu.bme.mit.trainbenchmark.railway.Route;

import java.util.Collection;

public class EMFUserTransformationSemaphoreNeighbor extends EMFUserTransformation<Route> {

	public EMFUserTransformationSemaphoreNeighbor(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Route> routes) {
		for (final Route route : routes) {
			route.setEntry(null);
			driver.getContainer().getInvalids().add(route);
		}
	}
}
