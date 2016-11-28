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
package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.inject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation.SQLiteTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class SQLiteTransformationInjectRouteSensor extends SQLiteTransformation<SqlRouteSensorInjectMatch> {

	final String setBindings = "INSERT OR REPLACE INTO Variables VALUES ('route', ?), ('sensor', ?);";

	public SQLiteTransformationInjectRouteSensor(final SQLiteDriver driver, final String workspaceDir)
			throws IOException {
		super(driver, workspaceDir, RailwayOperation.ROUTESENSOR_INJECT);
	}

	@Override
	public void activate(final Collection<SqlRouteSensorInjectMatch> matches) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = driver.getConnection().prepareStatement(setBindings);
		}

		for (final SqlRouteSensorInjectMatch match : matches) {
			preparedUpdateStatement.setLong(1, match.getRoute());
			preparedUpdateStatement.setLong(2, match.getSensor());
			preparedUpdateStatement.executeUpdate();

			driver.getConnection().createStatement().executeUpdate(updateQuery);
		}
	}

}
