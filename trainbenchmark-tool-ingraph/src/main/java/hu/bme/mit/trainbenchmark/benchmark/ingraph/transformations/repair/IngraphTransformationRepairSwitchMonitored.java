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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;

import java.util.Collection;

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.IngraphTransformation;

public class IngraphTransformationRepairSwitchMonitored extends IngraphTransformation<IngraphSwitchMonitoredMatch> {

	public IngraphTransformationRepairSwitchMonitored(final IngraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IngraphSwitchMonitoredMatch> matches) throws Exception {
		final Transaction transaction = driver.newTransaction();
		for (final IngraphSwitchMonitoredMatch match : matches) {
			final Long sw = match.getSw();
			final Long sensorID = driver.newKey();
//			transaction.add(sensorID, "type", SENSOR);
//			transaction.add(sw, MONITORED_BY, sensorID);
		}
	}
}
