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

import hu.bme.mit.incqueryds.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSwitchSetMatch;

import java.io.IOException;
import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;

public class IqdCoreTransformationRepairSwitchSet extends IqdCoreTransformationRepair<IqdCoreSwitchSetMatch> {

	public IqdCoreTransformationRepairSwitchSet(final IqdCoreDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IqdCoreSwitchSetMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IqdCoreSwitchSetMatch match : matches) {
			final Long sw = match.getSw();

			transaction.remove(sw, CURRENTPOSITION, match.getCurrentPosition());
			transaction.add(sw, CURRENTPOSITION, match.getPosition());
		}
	}

}
