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

import org.eclipse.emf.common.util.EList;

import hu.bme.mit.trainbenchmark.emf.EmfDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Region;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

public class EmfTransformationInjectConnectedSegments extends EmfTransformationInject<Segment> {

	public EmfTransformationInjectConnectedSegments(final EmfDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Segment> segments) throws IOException {
		for (final Segment segment1 : segments) {
			if (segment1.getConnectsTo().isEmpty()) {
				continue;
			}
			final Segment segment2 = RailwayFactory.eINSTANCE.createSegment();

			final TrackElement segment3 = segment1.getConnectsTo().get(0);

			// delete (segment1)-[:connectsTo]->(segment3)
			segment1.getConnectsTo().remove(segment3);
			// (segment1)-[:connectsTo]->(segment2)
			segment1.getConnectsTo().add(segment2);
			// (segment2)-[:connectsTo]->(segment3)
			segment2.getConnectsTo().add(segment3);
			
			// (segment2)-[:monitoredBy]->(sensor)
			final EList<Sensor> sensors = segment1.getMonitoredBy();
			segment2.getMonitoredBy().addAll(sensors);
			
			// add the segment2 to a Region to ensure proper containment hierarchy
			final Region region = (Region) segment1.eContainer();
			region.getElements().add(segment2);
		}
	}
}
