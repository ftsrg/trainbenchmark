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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.ocl.util.Bag;
import org.eclipse.ocl.util.Tuple;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSwitchSetMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

public class EclipseOCLSwitchSetChecker extends EclipseOCLChecker<EMFSwitchSetMatch> {

	public EclipseOCLSwitchSetChecker(final EMFDriver driver, final BenchmarkConfig bc) throws Exception {
		super(driver, bc);
	}

	@Override
	public Collection<EMFSwitchSetMatch> check() {
		matches = new ArrayList<>();

		final Bag<Tuple<?, ?>> bag = (Bag<Tuple<?, ?>>) queryEvaluator.evaluate(driver.getContainer());
		for (final Tuple<?, ?> tuple : bag) {
			final Semaphore semaphore = (Semaphore) tuple.getValue("semaphore");
			final Route route = (Route) tuple.getValue("route");
			final SwitchPosition swP = (SwitchPosition) tuple.getValue("swP");
			final Switch sw = (Switch) tuple.getValue("sw");
			matches.add(new EMFSwitchSetMatch(semaphore, route, swP, sw));
		}

		return matches;
	}

}
