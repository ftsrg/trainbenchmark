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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.ocl.util.Bag;
import org.eclipse.ocl.util.Tuple;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EclipseOclQuerySemaphoreNeighbor extends EclipseOclQuery<EmfSemaphoreNeighborMatch> {

	public EclipseOclQuerySemaphoreNeighbor(final EmfDriver driver, final String workspaceDir) throws Exception {
		super(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
	}

	@Override
	public Collection<EmfSemaphoreNeighborMatch> evaluate() {
		matches = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Bag<Tuple<?, ?>> bag = (Bag<Tuple<?, ?>>) queryEvaluator.evaluate(driver.getContainer());
		for (final Tuple<?, ?> tuple : bag) {
			final Semaphore semaphore = (Semaphore) tuple.getValue("semaphore");
			final Route route1 = (Route) tuple.getValue("route1");
			final Route route2 = (Route) tuple.getValue("route2");
			final Sensor sensor1 = (Sensor) tuple.getValue("sensor1");
			final Sensor sensor2 = (Sensor) tuple.getValue("sensor2");
			final TrackElement te1 = (TrackElement) tuple.getValue("te1");
			final TrackElement te2 = (TrackElement) tuple.getValue("te2");
			matches.add(new EmfSemaphoreNeighborMatch(semaphore, route1, route2, sensor1, sensor2, te1, te2));
		}

		return matches;
	}

}
