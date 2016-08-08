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
package hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.inject;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.ViatraTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class ViatraTransformationInjectConnectedSegments extends ViatraTransformation<ConnectedSegmentsInjectMatch> {

	public ViatraTransformationInjectConnectedSegments(final ViatraDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<ConnectedSegmentsInjectMatch> matches) throws IOException {
		for (final ConnectedSegmentsInjectMatch csim : matches) {
			// create (segment2) node
			final Segment segment2 = RailwayFactory.eINSTANCE.createSegment();
			final Region region = (Region) csim.getSegment1().eContainer();
			region.getElements().add(segment2);
			
			// (segment1)-[:connectsTo]->(segment2)
			csim.getSegment1().getConnectsTo().add(segment2);
			// (segment2)-[:connectsTo]->(segment3)
			segment2.getConnectsTo().add(csim.getSegment3());
			// (segment2)-[:monitoredBy]->(sensor)
			segment2.getMonitoredBy().add(csim.getSensor());

			// remove (segment1)-[:connectsTo]->(segment3)
			csim.getSegment1().getConnectsTo().remove(csim.getSegment3());
		}
	}

}
