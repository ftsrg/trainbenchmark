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
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import hu.bme.mit.IQDcore.WildcardInput;
import hu.bme.mit.IQDcore.WildcardInput.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreSwitchSensorMatch;

import java.util.Collection;

public class IQDCoreTransformationRepairSwitchSensor extends IQDCoreTransformationRepair<IQDCoreSwitchSensorMatch> {

	public IQDCoreTransformationRepairSwitchSensor(final WildcardInput jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<IQDCoreSwitchSensorMatch> matches) throws Exception {
		Transaction transaction = input.newTransaction();
		for (final IQDCoreSwitchSensorMatch match : matches) {
			final long sw = match.getSw();
			final long sensorID = input.newKey();
			transaction.add(sensorID, "type", SENSOR);
			transaction.add(sw, SENSOR_EDGE, sensorID);
		}
		input.processTransaction(transaction);
	}
}
