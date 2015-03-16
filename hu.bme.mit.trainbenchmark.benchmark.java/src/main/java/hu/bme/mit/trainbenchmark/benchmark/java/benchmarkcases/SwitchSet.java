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
package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Signal;
import hu.bme.mit.trainbenchmark.railway.SignalState;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class SwitchSet extends JavaBenchmarkCase<SwitchPosition> {

	@Override
	protected Collection<SwitchPosition> check() {
		results = new ArrayList<>();

		final TreeIterator<EObject> contents = container.eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			if (eObject instanceof Route) {
				final Route route = (Route) eObject;
				final Signal signal = route.getRoute_entry();
				if (signal == null) {
					continue;
				}
				if (signal.getSignal_currentState() == SignalState.GO) {
					for (final SwitchPosition swP : route.getRoute_switchPosition()) {
						final Switch sw = swP.getSwitchPosition_switch();
						if (sw.getSwitch_currentState() != swP.getSwitchPosition_switchState()) {
							results.add(swP);
						}
					}
				}
			}
		}

		return results;
	}

}
