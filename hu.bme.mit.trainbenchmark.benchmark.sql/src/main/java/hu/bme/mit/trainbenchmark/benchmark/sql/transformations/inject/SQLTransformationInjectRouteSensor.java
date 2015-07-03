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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.sql.constants.SQLConstants.ROUTE_ID;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationInjectRouteSensor extends SQLTransformationInject {

	public SQLTransformationInjectRouteSensor(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> routes) throws IOException {
		for (final Long route : routes) {
			try {
				// (route)-[:definedBy]->(sensor) edge
				final String deleteDefinedBy = "" + //
						"DELETE FROM " + DEFINED_BY + " " + //
						"WHERE " + ROUTE_ID + " = " + route + " " + //
						"LIMIT 1;";
				sqlDriver.getConnection().createStatement().executeUpdate(deleteDefinedBy);
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}

}
