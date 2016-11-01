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

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.IngraphTransformation;

public class IngraphTransformationRepairConnectedSegments extends IngraphTransformation<IngraphConnectedSegmentsMatch> {

	public IngraphTransformationRepairConnectedSegments(final IngraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IngraphConnectedSegmentsMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IngraphConnectedSegmentsMatch match : matches) {
//			transaction.remove(match.getSegment1(), CONNECTS_TO, match.getSegment2());
//			transaction.remove(match.getSegment2(), CONNECTS_TO, match.getSegment3());
//			transaction.add(match.getSegment1(), CONNECTS_TO, match.getSegment3());
//
//			transaction.remove(match.getSegment2(), "sensor", match.getSensor());
		}
	}
}
