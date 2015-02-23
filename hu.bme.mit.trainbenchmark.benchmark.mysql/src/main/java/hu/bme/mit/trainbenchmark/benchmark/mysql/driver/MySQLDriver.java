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
package hu.bme.mit.trainbenchmark.benchmark.mysql.driver;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class MySQLDriver extends DatabaseDriver {

	protected static final Map<String, String> EDGE_SOURCE_TYPES = ImmutableMap.of(ModelConstants.TRACKELEMENT_SENSOR, "TrackElement_id",
			ModelConstants.ROUTE_ROUTEDEFINITION, "Route_id");

	protected static final Map<String, String> EDGE_TARGET_TYPES = ImmutableMap.of(ModelConstants.TRACKELEMENT_SENSOR, "Sensor_id");

	protected static final Map<String, String> EDGE_TABLE = ImmutableMap.of(ModelConstants.ROUTE_ENTRY, ModelConstants.ROUTE);

	protected Connection con;

	public MySQLDriver(final Connection con) {
		this.con = con;
	}

	@Override
	public List<? extends Object> collectVertices(final String type) throws IOException {
		final List<Long> results = new ArrayList<>();

		final String query = String.format("SELECT * FROM %s;", type);
		try (ResultSet rs = con.createStatement().executeQuery(query)) {
			while (rs.next()) {
				results.add(rs.getLong("id"));
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}

		return results;
	}

	@Override
	public void deleteOneOutgoingEdge(final Object vertex, final String edgeType) throws IOException {
		deleteOutgoingEdges(vertex, edgeType, false);
	}

	@Override
	public void deleteAllOutgoingEdges(final Object vertex, final String edgeType) throws IOException {
		deleteOutgoingEdges(vertex, edgeType, true);
	}

	protected void deleteOutgoingEdges(final Object vertex, final String edgeType, final boolean all) throws IOException {
		final Long vertexId = (Long) vertex;
		try {
			final String q = String.format("DELETE FROM %s WHERE %s = %d" + (all ? "" : " LIMIT 1") + ";", edgeType,
					EDGE_SOURCE_TYPES.get(edgeType), vertexId);
			con.createStatement().executeUpdate(q);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deleteOutgoingEdge(final Object vertex, final String vertexType, final String edgeType) throws IOException {
		final Long vertexId = (Long) vertex;
		try {
			final String q = String.format("UPDATE `%s` SET `%s` = 0 WHERE id = %d;", vertexType, edgeType, vertexId);
			con.createStatement().executeUpdate(q);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deleteAllIncomingEdges(final Object vertex, final String edgeType, final String sourceVertexType) throws IOException {
		final Long vertexId = (Long) vertex;
		try {
			final String q = String.format("DELETE FROM %s WHERE %s = %d;", edgeType, EDGE_TARGET_TYPES.get(edgeType), vertexId);
			con.createStatement().executeUpdate(q);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void updateProperty(final Object vertex, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) throws IOException {
		final Long vertexId = (Long) vertex;
		try {
			con.createStatement().executeUpdate(
					String.format("UPDATE %s SET %s WHERE id = %d;", vertexType, attributeOperation.sqlUpdate(propertyName), vertexId));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void insertVertexWithEdge(final Object sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		try {
			final Long sourceVertexId = (Long) sourceVertex;
			final Statement st = con.createStatement();
			st.executeUpdate(String.format("INSERT INTO `%s` VALUES ();", targetVertexType), Statement.RETURN_GENERATED_KEYS);

			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					final long newVertexId = rs.getLong(1);
					st.executeUpdate(String.format("INSERT INTO `%s` (`TrackElement_id`, `Sensor_id`) VALUES (%d, %d);", edgeType,
							sourceVertexId, newVertexId));
				}
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

}
