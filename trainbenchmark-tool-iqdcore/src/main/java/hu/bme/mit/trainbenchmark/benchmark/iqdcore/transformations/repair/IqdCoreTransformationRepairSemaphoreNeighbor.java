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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.incqueryds.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.IqdCoreTransformation;

public class IqdCoreTransformationRepairSemaphoreNeighbor extends IqdCoreTransformation<IqdCoreSemaphoreNeighborMatch> {

	public IqdCoreTransformationRepairSemaphoreNeighbor(final IqdCoreDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IqdCoreSemaphoreNeighborMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IqdCoreSemaphoreNeighborMatch match : matches) {
			final Long route2 = match.getRoute2();
			final Long semaphore = match.getSemaphore();
			transaction.add(route2, ENTRY, semaphore);
		}
	}

}
