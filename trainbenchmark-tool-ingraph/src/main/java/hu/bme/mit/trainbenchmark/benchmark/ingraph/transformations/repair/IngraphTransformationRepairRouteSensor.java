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

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.IngraphTransformation;
import scala.collection.immutable.Map;

import java.io.IOException;
import java.util.Collection;

public class IngraphTransformationRepairRouteSensor extends IngraphTransformation<IngraphRouteSensorMatch> {

	public IngraphTransformationRepairRouteSensor(final IngraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<IngraphRouteSensorMatch> matches) throws IOException {
		final Transaction transaction = driver.newTransaction();
		for (final IngraphRouteSensorMatch match : matches) {
			final Long route = match.getRoute();
			final Long sensor = match.getSensor();

			transaction.add("_e4", new Map.Map2("route", route, "sensor", sensor));
		}
	}

}
