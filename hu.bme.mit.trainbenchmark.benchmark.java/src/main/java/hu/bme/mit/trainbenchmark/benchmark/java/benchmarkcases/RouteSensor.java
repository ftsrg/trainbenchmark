/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import java.util.ArrayList;

import Concept.Sensor;
import Concept.Switch;
import Concept.SwitchPosition;
import Concept.Trackelement;

public class RouteSensor extends JavaBenchmarkCase<Sensor> {

	@Override
	public String getName() {
		return "RouteSensor";
	}

	protected int constraintCheck() {
		invalids = new ArrayList<Sensor>();

		for (Object eObject : pack.getContains()) {
			if (eObject instanceof Sensor) {
				Sensor sensor = (Sensor) eObject;
				for (Trackelement te : sensor.getSensor_trackElement()) {
					if (te instanceof Switch) {
						Switch aSwitch = (Switch) te;
						for (SwitchPosition sp : aSwitch.getSwitch_switchPosition()) {
							if (!sp.getSwitchPosition_route().getRoute_routeDefinition().contains(sensor)) {
								invalids.add(sensor);
							}
						}
					}
				}
			}
		}

		return invalids.size();
	}

	@Override
	public void check() {
		bmr.startStopper();
		bmr.addInvalid(constraintCheck());
		bmr.addCheckTime();
	}

}
