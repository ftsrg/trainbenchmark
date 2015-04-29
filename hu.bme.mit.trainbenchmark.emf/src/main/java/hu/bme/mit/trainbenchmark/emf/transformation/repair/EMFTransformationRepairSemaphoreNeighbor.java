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
package hu.bme.mit.trainbenchmark.emf.transformation.repair;

import hu.bme.mit.trainbenchmark.emf.matches.EMFSemaphoreNeighborMatch;

import java.util.Collection;

public class EMFTransformationRepairSemaphoreNeighbor extends EMFRepairTransformation<EMFSemaphoreNeighborMatch> {

	@Override
	public void rhs(final Collection<EMFSemaphoreNeighborMatch> matches) {
		for (final EMFSemaphoreNeighborMatch match : matches) {
			match.getRoute2().setEntry(match.getSemaphore());
		}
	}

}
