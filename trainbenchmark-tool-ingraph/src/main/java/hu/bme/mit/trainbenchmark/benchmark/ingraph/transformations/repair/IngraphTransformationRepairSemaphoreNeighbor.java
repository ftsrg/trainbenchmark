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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.IngraphTransformation;
import scala.collection.immutable.Map;

public class IngraphTransformationRepairSemaphoreNeighbor extends IngraphTransformation<IngraphSemaphoreNeighborMatch> {

	public IngraphTransformationRepairSemaphoreNeighbor(final IngraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IngraphSemaphoreNeighborMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IngraphSemaphoreNeighborMatch match : matches) {
			final Long route2 = match.getRoute2();
			final Long semaphore = match.getSemaphore();
			transaction.add(ENTRY, new Map.Map2<>("route2", route2, "semaphore", semaphore));
		}
	}

}
