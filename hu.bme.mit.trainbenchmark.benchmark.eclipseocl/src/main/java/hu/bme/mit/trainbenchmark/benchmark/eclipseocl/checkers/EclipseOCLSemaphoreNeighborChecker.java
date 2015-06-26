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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.util.Bag;
import org.eclipse.ocl.util.Tuple;

public class EclipseOCLSemaphoreNeighborChecker extends EclipseOCLChecker<EMFSemaphoreNeighborMatch> {

	public EclipseOCLSemaphoreNeighborChecker(final EMFDriver driver, final BenchmarkConfig bc) throws IOException {
		super(driver, bc);
	}

	@Override
	protected EClassifier getContext() {
		return RailwayPackage.eINSTANCE.getSegment();
	}

	@Override
	public Collection<EMFSemaphoreNeighborMatch> check() {
		matches = new ArrayList<>();

		final Bag<Tuple<?, ?>> bag = (Bag<Tuple<?, ?>>) queryEvaluator.evaluate(driver.getContainer());
		for (final Tuple<?, ?> tuple : bag) {
			final Semaphore semaphore = (Semaphore) tuple.getValue("semaphore");
			final Route route1 = (Route) tuple.getValue("route1");
			final Route route2 = (Route) tuple.getValue("route2");
			final Sensor sensor1 = (Sensor) tuple.getValue("sensor1");
			final Sensor sensor2 = (Sensor) tuple.getValue("sensor2");
			final TrackElement te1 = (TrackElement) tuple.getValue("te1");
			final TrackElement te2 = (TrackElement) tuple.getValue("te2");
			matches.add(new EMFSemaphoreNeighborMatch(semaphore, route1, route2, sensor1, sensor2, te1, te2));
		}

		return matches;
	}

}
