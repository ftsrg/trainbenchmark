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

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EMFAPISemaphoreNeighborChecker extends EMFAPIChecker<EMFSemaphoreNeighborMatch> {

	public EMFAPISemaphoreNeighborChecker(final EMFDriver<?> emfDriver) {
		super(emfDriver);
	}

	@Override
	public Collection<EMFSemaphoreNeighborMatch> check() {
		matches = new ArrayList<>();

		final EList<Route> routes = emfDriver.getContainer().getRoutes();
		for (Route route1 : routes) {
			// (route1:Route)-[:exit]->(semaphore:Semaphore)
			final Semaphore semaphore = route1.getExit();
			if (semaphore == null) {
				continue;
			}

			// (route1:Route)-[:gathers]->(sensor1:Sensor)
			for (final Sensor sensor1 : route1.getGathers()) {
				// (sensor1:Sensor)<-[:monitoredBy]-(te1:TrackElement)
				for (final TrackElement te1 : sensor1.getMonitors()) {
					// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
					for (final TrackElement te2 : te1.getConnectsTo()) {
						// (te2:TrackElement)<-[:monitoredBy]-(sensor2:Sensor)
						for (Sensor sensor2 : te2.getMonitoredBy()) {
							// (route2:Route)-[:gathers]->(sensor2:Sensor)
							for (Route route2 : routes) {
								if (!route2.getGathers().contains(sensor2)) {
									continue;
								}
								
								// route1 != route2
								if (route1.equals(route2)) {
									continue;
								}
								
								// (route2:Route)-[:entry]->(semaphore:Semaphore) NAC
								if (!semaphore.equals(route2.getEntry())) {
									matches.add(new EMFSemaphoreNeighborMatch(semaphore, route1, route2, sensor1, sensor2, te1, te2));
								}
							}
						}
					}
				}
			}
		}

		return matches;
	}

}
