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
package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class EmfTransformationInjectConnectedSegments<TDriver extends EmfDriver, TConnectedSegmentsInjectMatch extends EmfConnectedSegmentsInjectMatch>
		extends EmfTransformation<TConnectedSegmentsInjectMatch, TDriver> {

	public EmfTransformationInjectConnectedSegments(final TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TConnectedSegmentsInjectMatch> matches) throws IOException {
		for (final EmfConnectedSegmentsInjectMatch match : matches) {
			final Segment segment2 = RailwayFactory.eINSTANCE.createSegment();

			// delete (segment1)-[:connectsTo]->(segment3)
			match.getSegment1().getConnectsTo().remove(match.getSegment3());
			// (segment1)-[:connectsTo]->(segment2)
			match.getSegment1().getConnectsTo().add(segment2);
			// (segment2)-[:connectsTo]->(segment3)
			segment2.getConnectsTo().add(match.getSegment3());

			// (segment2)-[:monitoredBy]->(sensor)
			segment2.getMonitoredBy().add(match.getSensor());

			// add the segment2 to a Region to ensure proper containment hierarchy
			final Region region = (Region) match.getSegment1().eContainer();
			region.getElements().add(segment2);
		}
	}
}
