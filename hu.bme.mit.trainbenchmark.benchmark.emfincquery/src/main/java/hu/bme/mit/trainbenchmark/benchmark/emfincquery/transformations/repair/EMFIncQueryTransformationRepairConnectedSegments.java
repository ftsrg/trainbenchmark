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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.EMFIncQueryTransformation;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.ecore.util.EcoreUtil;

public class EMFIncQueryTransformationRepairConnectedSegments extends EMFIncQueryTransformation<ConnectedSegmentsMatch> {

	public EMFIncQueryTransformationRepairConnectedSegments(final EMFIncQueryDriver<?> driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<ConnectedSegmentsMatch> matches) throws IOException {
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
