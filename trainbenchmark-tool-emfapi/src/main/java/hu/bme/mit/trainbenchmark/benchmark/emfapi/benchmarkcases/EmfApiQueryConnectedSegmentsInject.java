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
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfApiQueryConnectedSegmentsInject<TDriver extends EmfDriver> extends EmfApiQuery<EmfConnectedSegmentsInjectMatch, TDriver> {

	public EmfApiQueryConnectedSegmentsInject(final TDriver driver) {
		super(RailwayQuery.CONNECTEDSEGMENTS_INJECT, driver);
	}

	@Override
	public Collection<EmfConnectedSegmentsInjectMatch> evaluate() {
		final List<EmfConnectedSegmentsInjectMatch> matches = new ArrayList<>();

		final EList<Region> regions = driver.getContainer().getRegions();
		final EClass clazz = RailwayPackage.eINSTANCE.getSegment();

		for (final Region region : regions) {
			for (final TrackElement te1 : region.getElements()) {
				if (te1.eClass().isSuperTypeOf(clazz)) {
					final Segment segment1 = (Segment) te1;

					for (final TrackElement te3 : segment1.getConnectsTo()) {
						if (te3.eClass().isSuperTypeOf(clazz)) {
							final Segment segment3 = (Segment) te3;

							for (final Sensor sensor : segment1.getMonitoredBy()) {
								if (!segment3.getMonitoredBy().contains(sensor)) {
									continue;
								}

								matches.add(new EmfConnectedSegmentsInjectMatch(sensor, segment1, segment3));
							}
						}
					}
				}
			}
		}

		return matches;
	}

}
