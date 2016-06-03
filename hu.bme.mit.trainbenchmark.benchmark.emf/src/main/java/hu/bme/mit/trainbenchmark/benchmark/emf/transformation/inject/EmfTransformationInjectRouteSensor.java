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
package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.emf.EmfDriver;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;

public class EmfTransformationInjectRouteSensor extends EmfTransformationInject<Route> {

	public EmfTransformationInjectRouteSensor(final EmfDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Route> routes) throws IOException {
		for (final Route route : routes) {
			final EList<Sensor> gatherss = route.getGathers();

			// delete edges
			gatherss.clear();
		}
	}

}
