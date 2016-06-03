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

package hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFRouteSensorMatch;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EMFAPIRouteSensorQuery<TDriver extends EMFDriver> extends EMFAPIModelQuery<EMFRouteSensorMatch, TDriver> {

	public EMFAPIRouteSensorQuery(final TDriver driver) {
		super(driver);
	}

	@Override
	public Collection<EMFRouteSensorMatch> check() {
		matches = new ArrayList<>();

		final EList<Route> routes = driver.getContainer().getRoutes();
		// (route:Route)
		for (Route route : routes) {
			// (route)-[:follows]->(swP:SwitchPosition)
			for (final SwitchPosition swP : route.getFollows()) {
				// (swP:switchPosition)-[:target]->(sw:Switch)
				final Switch sw = swP.getTarget();
				if (sw == null) {
					continue;
				}

				// (switch:Switch)-[:monitoredBy]->(sensor:Sensor)
				final List<Sensor> sensors = sw.getMonitoredBy();

				// TODO check n-m edge
				for (Sensor sensor2 : sensors) {
					// (route)-[:gathers]->(sensor) NAC
					if (!route.getGathers().contains(sensor2)) {
						final EMFRouteSensorMatch match = new EMFRouteSensorMatch(route, sensor2, swP, sw);
						matches.add(match);
					}
				}
			}
		}

		return matches;
	}

}
