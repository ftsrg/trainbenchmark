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

import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.ArrayList;
import java.util.List;

public class RouteSensor extends JavaBenchmarkCase<Sensor> {

	@Override
	protected List<Sensor> check() {
		results = new ArrayList<>();

		for (final Object eObject : pack.getContains()) {
			if (eObject instanceof Sensor) {
				final Sensor sensor = (Sensor) eObject;
				for (final TrackElement te : sensor.getSensor_trackElement()) {
					if (te instanceof Switch) {
						final Switch aSwitch = (Switch) te;
						for (final SwitchPosition sp : aSwitch.getSwitch_switchPosition()) {
							if (!sp.getSwitchPosition_route().getRoute_routeDefinition().contains(sensor)) {
								results.add(sensor);
							}
						}
					}
				}
			}
		}
		
		return results;
	}

}
