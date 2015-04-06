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
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class JavaRouteSensor extends JavaBenchmarkCase<Sensor> {

	@Override
	protected Collection<Sensor> check() {
		matches = new ArrayList<>();

		final TreeIterator<EObject> contents = container.eAllContents();	
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			if (eObject instanceof Sensor) {
				final Sensor sensor = (Sensor) eObject;
				for (final TrackElement te : sensor.getElements()) {
					if (te instanceof Switch) {
						final Switch sw = (Switch) te;
						for (final SwitchPosition swP : sw.getPositions()) {
							if (!swP.getRoute().getDefinedBy().contains(sensor)) {
								matches.add(sensor);
							}
						}
					}
				}
			}
		}
		
		return matches;
	}

}
