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

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EMFAPISemaphoreNeighborChecker extends EMFAPIChecker<EMFSemaphoreNeighborMatch> {

	public EMFAPISemaphoreNeighborChecker(final EMFDriver emfDriver) {
		super(emfDriver);
	}

	@Override
	public Collection<EMFSemaphoreNeighborMatch> check() {
		matches = new ArrayList<>();
		final TreeIterator<EObject> contents = emfDriver.getContainer().eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			// (route1:Route)
			if (!RailwayPackage.eINSTANCE.getRoute().isInstance(eObject)) {
				continue;
			}

			final Route route1 = (Route) eObject;
			
			// (route1:Route)-[:exit]->(semaphore:Semaphore)
			final Semaphore semaphore = route1.getExit();
			if (semaphore == null) {
				continue;
			}
			
			// (route1:Route)-[:gathers]->(sensor1:Sensor)
			outer: for (final Sensor sensor1 : route1.getGathers()) {
				// (sensor1:Sensor)-[:monitors]->(te1:TrackElement)
				for (final TrackElement te1 : sensor1.getMonitors()) {
					// (te1:TrackElement)-[:connectsTo]->(te2:TrackElement)
					for (final TrackElement te2 : te1.getConnectsTo()) {
						// TODO check n-m edge
						
						// (sensor2:Sensor)-[:monitoredBy]->(te2:TrackElement)
						final Sensor sensor2 = te2.getMonitoredBy().get(0);
						if (sensor2 == null) {
							continue;
						}
						
						// (sensor2:Sensor)-[eContainer()]->(route2:Route),
						final EObject route2object = sensor2.eContainer();
						if (!RailwayPackage.eINSTANCE.getRoute().isInstance(route2object)) {
							continue;
						}

						final Route route2 = (Route) route2object;

						// route1 != route2
						if (route1.equals(route2)) {
							continue;
						}

						// (route2:Route)-[:entry]->(semaphore:Semaphore) NAC
						if (!semaphore.equals(route2.getEntry())) {
							matches.add(new EMFSemaphoreNeighborMatch(semaphore, route1, route2, sensor1, sensor2, te1, te2));
							break outer;
						}
					}
				}
			}
		}

		return matches;
	}

}
