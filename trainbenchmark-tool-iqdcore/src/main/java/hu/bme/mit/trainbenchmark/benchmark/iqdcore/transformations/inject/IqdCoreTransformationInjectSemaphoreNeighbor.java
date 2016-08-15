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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.inject;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.incqueryds.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.IqdCoreTransformation;

public class IqdCoreTransformationInjectSemaphoreNeighbor extends IqdCoreTransformation<IqdCoreSemaphoreNeighborInjectMatch> {

	public IqdCoreTransformationInjectSemaphoreNeighbor(final IqdCoreDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IqdCoreSemaphoreNeighborInjectMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IqdCoreSemaphoreNeighborInjectMatch match : matches) {
			final Long route = match.getRoute();
			// TODO remove all outgoing entry edges of the route
			//transaction.remove(route, ENTRY, semaphore);
		}
	}

}
