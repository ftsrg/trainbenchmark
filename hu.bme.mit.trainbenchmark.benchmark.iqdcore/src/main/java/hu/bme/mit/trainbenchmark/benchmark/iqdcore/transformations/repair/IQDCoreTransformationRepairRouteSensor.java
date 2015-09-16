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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import hu.bme.mit.incquerydcore.WildcardInput;
import hu.bme.mit.incquerydcore.WildcardInput.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreRouteSensorMatch;

import java.io.IOException;
import java.util.Collection;

public class IQDCoreTransformationRepairRouteSensor extends IQDCoreTransformationRepair<IQDCoreRouteSensorMatch> {

	public IQDCoreTransformationRepairRouteSensor(final WildcardInput jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<IQDCoreRouteSensorMatch> matches) throws IOException {
		Transaction transaction = input.newTransaction();
		for (final IQDCoreRouteSensorMatch match : matches) {
			final Long route = match.getRoute();
			final Long sensor = match.getSensor();			
			transaction.add(route, DEFINED_BY, sensor);
		}
		input.processTransaction(transaction);
	}

}
