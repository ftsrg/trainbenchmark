/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationUserRouteSensor extends SQLTransformationUser {

	public SQLTransformationUserRouteSensor(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> routes) throws IOException {
		for (final Long route : routes) {
			try {
				// (route)-[:definedBy]->(sensor) edge
				final String deleteDefinedBy = String.format("" + //
						"DELETE FROM `%s` " + //
						"WHERE `Route_id` = %d " + //
						"LIMIT 1;", //
						DEFINED_BY, route);
				sqlDriver.getConnection().createStatement().executeUpdate(deleteDefinedBy);
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}

}
