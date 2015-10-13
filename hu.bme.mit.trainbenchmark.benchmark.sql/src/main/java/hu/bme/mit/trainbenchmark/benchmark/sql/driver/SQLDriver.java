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
import java.util.Comparator;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongComparator;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class SQLDriver<TBenchmarkConfig extends BenchmarkConfig> extends Driver<Long, TBenchmarkConfig> {

	protected String queryDefinition;
	protected Connection connection;
	protected PreparedStatement preparedQuery;

	public SQLDriver(final TBenchmarkConfig benchmarkConfig) {
		super(benchmarkConfig);
	}
	
	@Override
	public String getPostfix() {
		return ".sql";
	}

	@Override
	public Collection<SQLMatch> runQuery(final Query query, final String queryDefinition) throws SQLException {
		final Collection<SQLMatch> results = new ArrayList<>();

		if (preparedQuery == null) {
			preparedQuery = connection.prepareStatement(queryDefinition);
		}

		try (ResultSet rs = preparedQuery.executeQuery()) {
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

	public String getResourceDirectory() {
		return "/hu.bme.mit.trainbenchmark.benchmark.sql/src/main/resources/";
	}

}
