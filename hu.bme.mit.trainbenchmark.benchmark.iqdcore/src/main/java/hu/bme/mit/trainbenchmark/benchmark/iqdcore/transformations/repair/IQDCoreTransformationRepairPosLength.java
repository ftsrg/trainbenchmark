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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.incqueryds.WildcardInput.Transaction;
import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCorePosLengthMatch;

public class IQDCoreTransformationRepairPosLength extends IQDCoreTransformationRepair<IQDCorePosLengthMatch> {

	public IQDCoreTransformationRepairPosLength(final IQDCoreDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IQDCorePosLengthMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IQDCorePosLengthMatch match : matches) {
			final Long segment = match.getSegment();
			final int length = match.getLength();
			final int newLength = -length + 1;
			transaction.remove(segment, LENGTH, length);
			transaction.add(segment, LENGTH, newLength);
		}
		transaction.close();
	}

}
