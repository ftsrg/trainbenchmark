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

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfApiQueryPosLength<TDriver extends EmfDriver> extends EmfApiQuery<EmfPosLengthMatch, TDriver> {

	public EmfApiQueryPosLength(final TDriver driver) {
		super(RailwayQuery.POSLENGTH, driver);
	}

	@Override
	public Collection<EmfPosLengthMatch> evaluate() {
		final List<EmfPosLengthMatch> matches = new ArrayList<>();

		final EList<Region> regions = driver.getContainer().getRegions();
		for (final Region region : regions) {
			for (final TrackElement element : region.getElements()) {

				// (segment:Segment)
				if (RailwayPackage.eINSTANCE.getSegment().isInstance(element)) {
					final Segment segment = (Segment) element;

					// segment.length <= 0
					if (segment.getLength() <= 0) {
						matches.add(new EmfPosLengthMatch(segment));
					}
				}
			}
		}

		return matches;
	}

}
