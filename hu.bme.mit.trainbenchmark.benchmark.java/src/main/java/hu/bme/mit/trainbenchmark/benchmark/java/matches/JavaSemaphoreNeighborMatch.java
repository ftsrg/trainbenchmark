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
package hu.bme.mit.trainbenchmark.benchmark.java.matches;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class JavaSemaphoreNeighborMatch extends JavaMatch {

	public JavaSemaphoreNeighborMatch(final Semaphore semaphore, final Route route1, final Route route2, final Sensor sensor1,
			final Sensor sensor2, final TrackElement te1, final TrackElement te2) {
		super();
		match = new RailwayElement[] { semaphore, route1, route2, sensor1, sensor2, te1, te2 };
	}

	public Semaphore getSemaphore() {
		return (Semaphore) match[0];
	}

	public Route getRoute1() {
		return (Route) match[1];
	}

	public Route getRoute2() {
		return (Route) match[2];
	}

	public Sensor getSensor1() {
		return (Sensor) match[3];
	}

	public Sensor getSensor2() {
		return (Sensor) match[4];
	}

	public TrackElement getTe1() {
		return (TrackElement) match[5];
	}

	public TrackElement getTe2() {
		return (TrackElement) match[6];
	}

}
