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

import Concept.Route;
import Concept.Sensor;
import Concept.Signal;
import Concept.Trackelement;

public class SignalNeighbor extends JavaBenchmarkCase<Route> {

	@Override
	public String getName() {
		return "SignalNeighbor";
	}

	private int constraintCheck() {

		invalids = new ArrayList<Route>();
		for (Object eObject : pack.getContains()) {
			if (eObject instanceof Route) {
				Route aRoute = (Route) eObject;
				if (!(isValid(aRoute))) {
					invalids.add(aRoute);
				}
			}
		}

		return invalids.size();
	}

	private boolean isValid(Route route) {
		Signal exitSignal = route.getRoute_exit();
		for (Sensor sen1 : route.getRoute_routeDefinition()) {
			for (Trackelement te1 : sen1.getSensor_trackElement()) {
				for (Trackelement te2 : te1.getTrackElement_connectsTo()) {
					for (Sensor sen2 : te2.getTrackElement_sensor()) {
						boolean goodSensor = false;
						for (Object eObject : pack.getContains()) {
							if (eObject instanceof Route) {
								Route route2X = (Route) eObject;
								if ((route2X.getRoute_routeDefinition().contains(sen2)) && (route2X != route)) {
									goodSensor = true;
									break;
								}
							}
						}
						if (goodSensor) {
							for (Object eObject : pack.getContains()) {
								if (eObject instanceof Route) {
									Route route2 = (Route) eObject;
									if ((route2.getRoute_routeDefinition().contains(sen2)) && (route2.getRoute_entry() != null)
											&& (route2.getRoute_entry().equals(exitSignal))) {
										return true;
									}
								}
							}
							if (exitSignal != null) {
								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public void check() {
		bmr.startStopper();
		bmr.addInvalid(constraintCheck());
		bmr.addCheckTime();
	}

}
