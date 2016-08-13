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
import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation.SQLiteTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class SQLiteTransformationInjectConnectedSegments extends SQLiteTransformation<SqlConnectedSegmentsInjectMatch> {

	final String setBindings = "INSERT OR REPLACE INTO Variables VALUES ('sensor', ?), ('segment1', ?), ('segment3', ?);";
	
	public SQLiteTransformationInjectConnectedSegments(final SQLiteDriver driver, final Optional<String> workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS, Scenario.INJECT);
	}

	@Override
	public void activate(final Collection<SqlConnectedSegmentsInjectMatch> matches) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = driver.getConnection().prepareStatement(setBindings);
		}

		for (final SqlConnectedSegmentsInjectMatch match : matches) {
			preparedUpdateStatement.setLong(1, match.getSensor());
			preparedUpdateStatement.setLong(2, match.getSegment1());
			preparedUpdateStatement.setLong(3, match.getSegment3());
			preparedUpdateStatement.executeUpdate();

			driver.getConnection().createStatement().executeUpdate(updateQuery);
		}
	}
	
}
