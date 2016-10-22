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

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class SqlTransformationInjectRouteSensor<TSqlDriver extends SqlDriver> extends SqlTransformation<SqlRouteSensorInjectMatch, TSqlDriver> {

	public SqlTransformationInjectRouteSensor(final TSqlDriver driver, final String workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayOperation.ROUTESENSOR_INJECT);
	}

	@Override
	public void activate(final Collection<SqlRouteSensorInjectMatch> matches) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = driver.getConnection().prepareStatement(updateQuery);
		}

		for (final SqlRouteSensorInjectMatch match : matches) {
			preparedUpdateStatement.setLong(1, match.getRoute());
			preparedUpdateStatement.setLong(2, match.getSensor());
			preparedUpdateStatement.executeUpdate();
		}
	}

}
