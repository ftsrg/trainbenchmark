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
package hu.bme.mit.trainbenchmark.benchmark.sql.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SqlMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SqlDriver extends Driver<Long> {

	protected static final String COLLECT_VERTICES = "SELECT * FROM `%s`;";
	
	protected String queryDefinition;
	protected Connection connection;
	protected PreparedStatement preparedQuery;

	public SqlDriver() {
		super();
	}

	public Collection<SqlMatch> runStatement(final RailwayQuery query, final PreparedStatement statement) throws SQLException {
		final Collection<SqlMatch> results = new ArrayList<>();

		try (ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				final SqlMatch match = SqlMatch.createMatch(query, rs);
				results.add(match);
			}
		}

		return results;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public Collection<Long> collectVertices(final String type) throws SQLException {
		final Collection<Long> results = new ArrayList<>();

		final String query = String.format(COLLECT_VERTICES, type);
		try (ResultSet rs = connection.createStatement().executeQuery(query)) {
			while (rs.next()) {
				results.add(rs.getLong("id"));
			}
		}

		return results;
	}

	public String getResourceDirectory() {
		return "/hu.bme.mit.trainbenchmark.benchmark.sql/src/main/resources/";
	}

}
