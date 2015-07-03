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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.sql.constants.SQLConstants.ID_POSTFIX;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLRouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationRepairRouteSensor extends SQLTransformationRepair<SQLRouteSensorMatch> {

	public SQLTransformationRepairRouteSensor(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<SQLRouteSensorMatch> matches) throws SQLException {
		for (final SQLRouteSensorMatch match : matches) {
			final String update = String.format("" + //
					"INSERT INTO `%s` (`%s`, `%s`) " + //
					"VALUES (%d, %d);", //
					DEFINED_BY, ROUTE + ID_POSTFIX, ModelConstants.SENSOR + ID_POSTFIX, match.getRoute(), match.getSensor());
			sqlDriver.getConnection().createStatement().executeUpdate(update);
		}
	}
}
