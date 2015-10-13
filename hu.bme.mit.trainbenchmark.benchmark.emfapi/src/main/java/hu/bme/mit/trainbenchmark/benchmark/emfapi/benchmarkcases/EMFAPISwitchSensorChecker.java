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

package hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSwitchSensorMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Switch;

public class EMFAPISwitchSensorChecker extends EMFAPIChecker<EMFSwitchSensorMatch> {

	public EMFAPISwitchSensorChecker(final EMFDriver emfDriver) {
		super(emfDriver);
	}

	@Override
	public Collection<EMFSwitchSensorMatch> check() {
		matches = new ArrayList<>();
		final TreeIterator<EObject> contents = emfDriver.getContainer().eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			// (Switch)
			if (!RailwayPackage.eINSTANCE.getSwitch().isInstance(eObject)) {
				continue;
			}

			final Switch sw = (Switch) eObject;

			// (Switch)-[sensor]->() NAC
			if (sw.getSensor() == null) {
				matches.add(new EMFSwitchSensorMatch(sw));
			}
		}

		return matches;
	}
}
