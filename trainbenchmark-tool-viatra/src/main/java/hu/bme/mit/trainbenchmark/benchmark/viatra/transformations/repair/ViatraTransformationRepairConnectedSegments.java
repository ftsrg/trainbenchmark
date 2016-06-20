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
package hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.ecore.util.EcoreUtil;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.ViatraTransformation;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class ViatraTransformationRepairConnectedSegments extends ViatraTransformation<ConnectedSegmentsMatch> {

	public ViatraTransformationRepairConnectedSegments(final ViatraDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<ConnectedSegmentsMatch> matches) throws IOException {
		for (final ConnectedSegmentsMatch match : matches) {
			final Segment segment1 = match.getSegment1();
			final Segment segment2 = match.getSegment2();
			final Segment segment3 = match.getSegment3();

			segment1.getConnectsTo().remove(segment2);
			segment1.getConnectsTo().add(segment3);
			EcoreUtil.delete(segment2);
		}
	}
}
