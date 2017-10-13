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
package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.util.Collection;

public class EmfTransformationRepairPosLength<TDriver extends EmfDriver, TPosLengthMatch extends EmfPosLengthMatch>
		extends EmfTransformation<TPosLengthMatch, TDriver> {

	public EmfTransformationRepairPosLength(final TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TPosLengthMatch> matches) {
		for (final EmfPosLengthMatch match : matches) {
			final Segment segment = match.getSegment();
			final int newLength = -segment.getLength() + 1;
			segment.setLength(newLength);
		}
	}

}
