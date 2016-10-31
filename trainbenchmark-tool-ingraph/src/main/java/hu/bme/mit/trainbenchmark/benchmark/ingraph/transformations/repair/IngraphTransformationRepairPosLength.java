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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.IngraphTransformation;

public class IngraphTransformationRepairPosLength extends IngraphTransformation<IngraphPosLengthMatch> {

	public IngraphTransformationRepairPosLength(final IngraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IngraphPosLengthMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IngraphPosLengthMatch match : matches) {
			final Long segment = match.getSegment();
			final Integer length = match.getLength();
			final Integer newLength = -length + 1;
//			transaction.remove(segment, LENGTH, length);
//			transaction.add(segment, LENGTH, newLength);
		}
	}

}
