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
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;

public class EmfTransformationInjectSemaphoreNeighbor<TDriver extends EmfDriver, TSemaphoreNeighborInjectMatch extends EmfSemaphoreNeighborInjectMatch>
		extends EmfTransformation<TSemaphoreNeighborInjectMatch, TDriver> {

	public EmfTransformationInjectSemaphoreNeighbor(final TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TSemaphoreNeighborInjectMatch> matches) throws IOException {
		for (final TSemaphoreNeighborInjectMatch match : matches) {
			final Route route = match.getRoute();
			final Semaphore semaphore = match.getSemaphore();
			if (route.getEntry().equals(semaphore)) {
				route.setEntry(null);
			}
		}
	}
}
