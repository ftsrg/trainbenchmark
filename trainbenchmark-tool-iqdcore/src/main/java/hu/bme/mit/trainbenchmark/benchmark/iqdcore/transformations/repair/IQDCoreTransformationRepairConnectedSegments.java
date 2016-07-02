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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.incqueryds.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreConnectedSegmentsMatch;

public class IQDCoreTransformationRepairConnectedSegments extends IQDCoreTransformationRepair<IQDCoreConnectedSegmentsMatch> {

	public IQDCoreTransformationRepairConnectedSegments(final IQDCoreDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IQDCoreConnectedSegmentsMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IQDCoreConnectedSegmentsMatch match : matches) {
			transaction.remove(match.getSegment1(), CONNECTS_TO, match.getSegment2());
			transaction.remove(match.getSegment2(), CONNECTS_TO, match.getSegment3());
			transaction.add(match.getSegment1(), CONNECTS_TO, match.getSegment3());

			transaction.remove(match.getSegment2(), "sensor", match.getSensor());
		}
	}
}
