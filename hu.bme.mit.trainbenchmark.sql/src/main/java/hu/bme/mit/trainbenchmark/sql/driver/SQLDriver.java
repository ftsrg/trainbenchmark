/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.sql.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongComparator;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.sql.match.SQLMatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class SQLDriver extends Driver<Long> {

	protected Connection connection;

	// protected static final Map<String, String> EDGE_SOURCE_TYPES = ImmutableMap.of( //
	// ModelConstants.SENSOR_EDGE, "id", //
	// ModelConstants.DEFINED_BY, "Route_id", //
	// ModelConstants.CONNECTSTO, "TrackElement_id");
	//
	// protected static final Map<String, String> EDGE_TARGET_TYPES = ImmutableMap.of( //
	// ModelConstants.SENSOR_EDGE, "sensor", //
	// ModelConstants.CONNECTSTO, "TrackElement_id_connectsTo");
	//
	// protected static final Map<String, String> EDGE_TABLE = ImmutableMap.of(
	// //
	// ModelConstants.ENTRY, ModelConstants.ROUTE, ModelConstants.SENSOR_EDGE, ModelConstants.TRACKELEMENT, ModelConstants.CONNECTSTO,
	// ModelConstants.CONNECTSTO, ModelConstants.DEFINED_BY, ModelConstants.DEFINED_BY);

	@Override
	public String getExtension() {
		return ".sql";
	}

	@Override
	public List<SQLMatch> runQuery(final Query query, final String queryDefinition) throws IOException {
		final List<SQLMatch> results = new ArrayList<>();

		try (ResultSet rs = connection.createStatement().executeQuery(queryDefinition)) {
			while (rs.next()) {
				final SQLMatch match = SQLMatch.createMatch(query, rs);
				results.add(match);
			}

		} catch (final SQLException e) {
			throw new IOException(e);
		}

		return results;
	}

	public Connection getConnection() {
		return connection;
	}

	// create

	// read

	@Override
	public List<Long> collectVertices(final String type) throws IOException {
		final List<Long> results = new ArrayList<>();

		final String query = String.format("SELECT * FROM `%s`;", type);
		try (ResultSet rs = connection.createStatement().executeQuery(query)) {
			while (rs.next()) {
				results.add(rs.getLong("id"));
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}

		return results;
	}

	@Override
	public Comparator<Long> getElementComparator() {
		return new LongComparator();
	}

	//
	// @Override
	// public List<Long> collectOutgoingConnectedVertices(final Long sourceVertex, final String sourceVertexType,
	// final String targetVertexType, final String edgeType) throws IOException {
	// final List<Long> results = new ArrayList<>();
	//
	// try {
	// final Statement statement = con.createStatement();
	// final ResultSet rs = statement.executeQuery(String.format("SELECT * FROM `%s` WHERE `%s` = %s;", EDGE_TABLE.get(edgeType),
	// EDGE_SOURCE_TYPES.get(edgeType), sourceVertex));
	// while (rs.next()) {
	// results.add(rs.getLong(2));
	// }
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	// return results;
	//

}
