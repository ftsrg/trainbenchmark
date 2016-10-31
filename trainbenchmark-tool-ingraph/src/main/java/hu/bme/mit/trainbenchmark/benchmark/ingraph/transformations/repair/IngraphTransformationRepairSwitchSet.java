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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.IngraphTransformation;

public class IngraphTransformationRepairSwitchSet extends IngraphTransformation<IngraphSwitchSetMatch> {

	public IngraphTransformationRepairSwitchSet(final IngraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IngraphSwitchSetMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IngraphSwitchSetMatch match : matches) {
			final Long sw = match.getSw();

			transaction.remove(sw, CURRENTPOSITION, match.getCurrentPosition());
			transaction.add(sw, CURRENTPOSITION, match.getPosition());
		}
	}

}
