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
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EclipseOclQueryRouteSensor extends EclipseOclQuery<EmfRouteSensorMatch> {

	public EclipseOclQueryRouteSensor(final EmfDriver driver, final String workspaceDir) throws Exception {
		super(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
	}

	@Override
	public Collection<EmfRouteSensorMatch> evaluate() {
		matches = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final Bag<Tuple<?, ?>> bag = (Bag<Tuple<?, ?>>) queryEvaluator.evaluate(driver.getContainer());
		for (final Tuple<?, ?> tuple : bag) {
			final Route route = (Route) tuple.getValue("route");
			final Sensor sensor = (Sensor) tuple.getValue("sensor");
			final SwitchPosition swP = (SwitchPosition) tuple.getValue("swP");
			final Switch sw = (Switch) tuple.getValue("sw");
			matches.add(new EmfRouteSensorMatch(route, sensor, swP, sw));
		}

		return matches;
	}

}
