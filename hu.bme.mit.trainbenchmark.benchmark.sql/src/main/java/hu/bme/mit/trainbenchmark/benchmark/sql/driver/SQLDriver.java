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

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongComparator;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class SQLDriver extends Driver<Long> {

	protected Connection connection;

	@Override
	public String getExtension() {
		return ".sql";
	}

	@Override
	public List<SQLMatch> runQuery(final Query query, final String queryDefinition) throws SQLException  {
		final List<SQLMatch> results = new ArrayList<>();

		try (ResultSet rs = connection.createStatement().executeQuery(queryDefinition)) {
			while (rs.next()) {
				final SQLMatch match = SQLMatch.createMatch(query, rs);
				results.add(match);
			}
		}

		return results;
	}

	public Connection getConnection() {
		return connection;
	}

	// read

	@Override
	public List<Long> collectVertices(final String type) throws SQLException {
		final List<Long> results = new ArrayList<>();

		final String query = String.format("SELECT * FROM `%s`;", type);
		try (ResultSet rs = connection.createStatement().executeQuery(query)) {
			while (rs.next()) {
				results.add(rs.getLong("id"));
			}
		}

		return results;
	}

	@Override
	public Comparator<Long> getElementComparator() {
		return new LongComparator();
	}

}
