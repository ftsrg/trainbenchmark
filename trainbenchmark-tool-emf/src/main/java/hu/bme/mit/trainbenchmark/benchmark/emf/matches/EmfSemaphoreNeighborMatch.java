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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((route1 == null) ? 0 : route1.hashCode());
		result = prime * result + ((route2 == null) ? 0 : route2.hashCode());
		result = prime * result + ((semaphore == null) ? 0 : semaphore.hashCode());
		result = prime * result + ((sensor1 == null) ? 0 : sensor1.hashCode());
		result = prime * result + ((sensor2 == null) ? 0 : sensor2.hashCode());
		result = prime * result + ((te1 == null) ? 0 : te1.hashCode());
		result = prime * result + ((te2 == null) ? 0 : te2.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EmfSemaphoreNeighborMatch other = (EmfSemaphoreNeighborMatch) obj;
		if (route1 == null) {
			if (other.route1 != null)
				return false;
		} else if (!route1.equals(other.route1))
			return false;
		if (route2 == null) {
			if (other.route2 != null)
				return false;
		} else if (!route2.equals(other.route2))
			return false;
		if (semaphore == null) {
			if (other.semaphore != null)
				return false;
		} else if (!semaphore.equals(other.semaphore))
			return false;
		if (sensor1 == null) {
			if (other.sensor1 != null)
				return false;
		} else if (!sensor1.equals(other.sensor1))
			return false;
		if (sensor2 == null) {
			if (other.sensor2 != null)
				return false;
		} else if (!sensor2.equals(other.sensor2))
			return false;
		if (te1 == null) {
			if (other.te1 != null)
				return false;
		} else if (!te1.equals(other.te1))
			return false;
		if (te2 == null) {
			if (other.te2 != null)
				return false;
		} else if (!te2.equals(other.te2))
			return false;
		return true;
	}

}
