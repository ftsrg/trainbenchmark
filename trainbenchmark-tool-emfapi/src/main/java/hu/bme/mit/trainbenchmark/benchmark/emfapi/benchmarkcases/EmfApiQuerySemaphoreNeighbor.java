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

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfApiQuerySemaphoreNeighbor<TDriver extends EmfDriver> extends EmfApiQuery<EmfSemaphoreNeighborMatch, TDriver> {

	public EmfApiQuerySemaphoreNeighbor(final TDriver driver) {
		super(RailwayQuery.SEMAPHORENEIGHBOR, driver);
	}

	@Override
	public Collection<EmfSemaphoreNeighborMatch> evaluate() {
		final List<EmfSemaphoreNeighborMatch> matches = new ArrayList<>();

		final EList<Route> routes = driver.getContainer().getRoutes();
		for (final Route route1 : routes) {
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
						for (final Sensor sensor2 : te2.getMonitoredBy()) {
							// (route2:Route)-[:gathers]->(sensor2:Sensor)
							for (final Route route2 : routes) {
								if (!route2.getGathers().contains(sensor2)) {
									continue;
								}
								
								// route1 != route2
								if (route1.equals(route2)) {
									continue;
								}
								
								// (route2:Route)-[:entry]->(semaphore:Semaphore) NAC
								if (!semaphore.equals(route2.getEntry())) {
									matches.add(new EmfSemaphoreNeighborMatch(semaphore, route1, route2, sensor1, sensor2, te1, te2));
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
