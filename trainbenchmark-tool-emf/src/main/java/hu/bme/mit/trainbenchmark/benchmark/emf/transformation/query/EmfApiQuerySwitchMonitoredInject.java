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

package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfApiQuerySwitchMonitoredInject<TDriver extends EmfDriver> extends EmfApiQuery<EmfSwitchMonitoredInjectMatch, TDriver> {

	public EmfApiQuerySwitchMonitoredInject(final TDriver driver) {
		super(RailwayQuery.SWITCHMONITORED_INJECT, driver);
	}

	@Override
	public Collection<EmfSwitchMonitoredInjectMatch> evaluate() {
		final List<EmfSwitchMonitoredInjectMatch> matches = new ArrayList<>();

		final EList<Region> regions = driver.getContainer().getRegions();
		final EClass clazz = RailwayPackage.eINSTANCE.getSwitch();
		
		for (final Region region : regions) {
			for (final TrackElement te : region.getElements()) {
				if (te.eClass().isSuperTypeOf(clazz)) {
					matches.add(new EmfSwitchMonitoredInjectMatch((Switch) te));
				}
			}
		}
		
		return matches;
	}
}
