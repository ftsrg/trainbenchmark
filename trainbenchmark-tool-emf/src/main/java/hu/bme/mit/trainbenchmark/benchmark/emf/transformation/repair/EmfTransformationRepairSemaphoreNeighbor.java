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
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;

import java.util.Collection;

public class EmfTransformationRepairSemaphoreNeighbor<TDriver extends EmfDriver, TSemaphoreNeighborMatch extends EmfSemaphoreNeighborMatch>
		extends EmfTransformation<TSemaphoreNeighborMatch, TDriver> {

	public EmfTransformationRepairSemaphoreNeighbor(final TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TSemaphoreNeighborMatch> matches) {
		for (final EmfSemaphoreNeighborMatch match : matches) {
			match.getRoute2().setEntry(match.getSemaphore());
		}
	}

}
