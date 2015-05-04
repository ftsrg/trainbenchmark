/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.Collection;

public class EMFTransformationUserConnectedSegments extends EMFTransformationUser<Segment> {

	public EMFTransformationUserConnectedSegments(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Segment> segments) {
		for (final Segment segment1 : segments) {
			if (segment1.getConnectsTo().isEmpty()) {
				continue;
			}
			final Segment segmentX = RailwayFactory.eINSTANCE.createSegment();
			driver.getContainer().getInvalids().add(segmentX);

			final TrackElement segment2 = segment1.getConnectsTo().get(0);
			segment1.getConnectsTo().remove(segment2);
			segment1.getConnectsTo().add(segmentX);
			segmentX.getConnectsTo().add(segment2);
		}
	}
}
