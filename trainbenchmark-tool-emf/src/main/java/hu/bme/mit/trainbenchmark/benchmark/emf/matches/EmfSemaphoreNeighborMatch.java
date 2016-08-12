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

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfSemaphoreNeighborMatch extends EmfMatch implements SemaphoreNeighborMatch {

	protected final Semaphore semaphore;
	protected final Route route1;
	protected final Route route2;
	protected final Sensor sensor1;
	protected final Sensor sensor2;
	protected final TrackElement te1;
	protected final TrackElement te2;

	public EmfSemaphoreNeighborMatch(final Semaphore semaphore, final Route route1, final Route route2, final Sensor sensor1, final Sensor sensor2,
			final TrackElement te1, final TrackElement te2) {
		super();
		this.semaphore = semaphore;
		this.route1 = route1;
		this.route2 = route2;
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		this.te1 = te1;
		this.te2 = te2;
	}

	@Override
	public Semaphore getSemaphore() {
		return semaphore;
	}

	@Override
	public Route getRoute1() {
		return route1;
	}

	@Override
	public Route getRoute2() {
		return route2;
	}

	@Override
	public Sensor getSensor1() {
		return sensor1;
	}

	@Override
	public Sensor getSensor2() {
		return sensor2;
	}

	@Override
	public TrackElement getTe1() {
		return te1;
	}

	@Override
	public TrackElement getTe2() {
		return te2;
	}

}
