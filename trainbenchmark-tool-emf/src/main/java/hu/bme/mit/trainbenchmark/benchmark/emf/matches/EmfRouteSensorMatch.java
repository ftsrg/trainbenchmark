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
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EmfRouteSensorMatch extends EmfMatch implements RouteSensorMatch {

	public EmfRouteSensorMatch(final Route route, final Sensor sensor, final SwitchPosition switchPosition, final Switch sw) {
		super();
		match = new RailwayElement[] { route, sensor, switchPosition, sw };
	}

	public Route getRoute() {
		return (Route) match[0];
	}

	public Sensor getSensor() {
		return (Sensor) match[1];
	}

	public SwitchPosition getSwP() {
		return (SwitchPosition) match[2];
	}

	public Switch getSw() {
		return (Switch) match[3];
	}

}
