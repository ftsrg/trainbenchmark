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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationInjectSemaphoreNeighbor extends SQLTransformationInject {

	public SQLTransformationInjectSemaphoreNeighbor(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> routes) throws IOException {
		for (final Long route : routes) {
			try {
				// (route)-[:entry]->(semaphore) edge
				final String deleteDefinedBy = "" + //
						"UPDATE " + ROUTE + " " + //
						"SET " + ENTRY + " = NULL " + //
						"WHERE " + ID + " = " + route + ";";
				sqlDriver.getConnection().createStatement().executeUpdate(deleteDefinedBy);
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}

}
