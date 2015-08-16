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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLRouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationRepairRouteSensor extends SQLTransformationRepair<SQLRouteSensorMatch> {

	public SQLTransformationRepairRouteSensor(final SQLDriver sqlDriver, final BenchmarkConfig bc)
			throws IOException {
		super(sqlDriver, bc);
	}

	@Override
	public void rhs(final Collection<SQLRouteSensorMatch> matches) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = sqlDriver.getConnection().prepareStatement(updateQuery);
		}

		for (final SQLRouteSensorMatch match : matches) {
			preparedUpdateStatement.setLong(1, match.getRoute());
			preparedUpdateStatement.setLong(2, match.getSensor());
			preparedUpdateStatement.executeUpdate();
		}
	}
}
