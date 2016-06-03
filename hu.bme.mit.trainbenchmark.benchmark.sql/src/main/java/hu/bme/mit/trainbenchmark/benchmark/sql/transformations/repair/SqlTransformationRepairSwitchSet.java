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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SqlSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SqlTransformationRepairSwitchSet extends SqlTransformationRepair<SqlSwitchSetMatch> {

	public SqlTransformationRepairSwitchSet(final SqlDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query)
			throws IOException {
		super(driver, benchmarkConfig, query);
	}

	@Override
	public void activate(final Collection<SqlSwitchSetMatch> matches) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = driver.getConnection().prepareStatement(updateQuery);
		}

		for (final SqlSwitchSetMatch match : matches) {
			preparedUpdateStatement.setLong(1, match.getPosition());
			preparedUpdateStatement.setLong(2, match.getSw());
			preparedUpdateStatement.executeUpdate();
		}
	}

}
