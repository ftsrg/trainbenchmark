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

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFPosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EMFAPIPosLengthChecker extends EMFAPIChecker<EMFPosLengthMatch> {

	public EMFAPIPosLengthChecker(final EMFDriver<?> emfDriver) {
		super(emfDriver);
	}

	@Override
	public Collection<EMFPosLengthMatch> check() {
		matches = new ArrayList<>();

		final EList<Region> regions = emfDriver.getContainer().getRegions();
		for (Region region : regions) {
			for (TrackElement element : region.getElements()) {

				// (segment:Segment)
				if (RailwayPackage.eINSTANCE.getSegment().isInstance(element)) {
					final Segment segment = (Segment) element;

					// segment.length <= 0
					if (segment.getLength() <= 0) {
						matches.add(new EMFPosLengthMatch(segment));
					}
				}
			}
		}

		return matches;
	}

}
