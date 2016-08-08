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
package hu.bme.mit.trainbenchmark.benchmark.emf.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfSemaphoreNeighborInjectMatch extends EmfMatch implements SemaphoreNeighborInjectMatch {

	public EmfSemaphoreNeighborInjectMatch(final Semaphore semaphore, final Route route1, final Route route2, final Sensor sensor1,
			final Sensor sensor2, final TrackElement te1, final TrackElement te2) {
		super();
		match = new RailwayElement[] { semaphore, route1, route2, sensor1, sensor2, te1, te2 };
	}

	@Override
	public Route getRoute() {
		return (Route) match[0];
	}

}
