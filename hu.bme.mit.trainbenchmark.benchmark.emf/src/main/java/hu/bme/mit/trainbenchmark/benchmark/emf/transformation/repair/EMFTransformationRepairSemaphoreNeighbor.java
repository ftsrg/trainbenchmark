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

import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public class EMFTransformationRepairSemaphoreNeighbor<TDriver extends EMFDriver> extends EMFTransformationRepair<EMFSemaphoreNeighborMatch, TDriver> {

	public EMFTransformationRepairSemaphoreNeighbor(TDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<EMFSemaphoreNeighborMatch> matches) {
		for (final EMFSemaphoreNeighborMatch snm : matches) {
			snm.getRoute2().setEntry(snm.getSemaphore());
		}
	}

}
