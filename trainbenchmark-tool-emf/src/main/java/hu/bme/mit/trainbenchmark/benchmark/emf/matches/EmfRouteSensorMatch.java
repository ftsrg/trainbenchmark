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

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EmfRouteSensorMatch extends EmfMatch implements RouteSensorMatch {
	
	protected final Route route;
	protected final Sensor sensor;
	protected final SwitchPosition swP;
	protected final Switch sw;

	public EmfRouteSensorMatch(final Route route, final Sensor sensor, final SwitchPosition swP, final Switch sw) {
		super();
		this.route = route;
		this.sensor = sensor;
		this.swP = swP;
		this.sw = sw;
	}

	@Override
	public Route getRoute() {
		return route;
	}

	@Override
	public Sensor getSensor() {
		return sensor;
	}

	@Override
	public SwitchPosition getSwP() {
		return swP;
	}

	@Override
	public Switch getSw() {
		return sw;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result + ((sw == null) ? 0 : sw.hashCode());
		result = prime * result + ((swP == null) ? 0 : swP.hashCode());
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
		final EmfRouteSensorMatch other = (EmfRouteSensorMatch) obj;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		if (sw == null) {
			if (other.sw != null)
				return false;
		} else if (!sw.equals(other.sw))
			return false;
		if (swP == null) {
			if (other.swP != null)
				return false;
		} else if (!swP.equals(other.swP))
			return false;
		return true;
	}

}
