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

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class SqlTransformationRepairRouteSensor<TSqlDriver extends SqlDriver> extends SqlTransformation<SqlRouteSensorMatch, TSqlDriver> {

	public SqlTransformationRepairRouteSensor(final TSqlDriver driver, final String workspaceDir)
			throws IOException {
		super(driver, workspaceDir, RailwayQuery.ROUTESENSOR, Scenario.REPAIR);
	}

	@Override
	public void activate(final Collection<SqlRouteSensorMatch> matches) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = driver.getConnection().prepareStatement(updateQuery);
		}

		for (final SqlRouteSensorMatch match : matches) {
			preparedUpdateStatement.setLong(1, match.getRoute());
			preparedUpdateStatement.setLong(2, match.getSensor());
			preparedUpdateStatement.executeUpdate();
		}
	}

}
