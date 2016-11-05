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

package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;

public class EmfApiQueryRouteSensorInject<TDriver extends EmfDriver> extends EmfApiQuery<EmfRouteSensorInjectMatch, TDriver> {

	public EmfApiQueryRouteSensorInject(final TDriver driver) {
		super(RailwayQuery.ROUTESENSOR_INJECT, driver);
	}

	@Override
	public Collection<EmfRouteSensorInjectMatch> evaluate() {
		final List<EmfRouteSensorInjectMatch> matches = new ArrayList<>();

		for (final Route route : driver.getContainer().getRoutes()) {
			for (final Sensor sensor : route.getRequires()) {
				matches.add(new EmfRouteSensorInjectMatch(route, sensor));				
			}
		}

		return matches;
	}

}
