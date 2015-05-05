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
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.sql.match.SQLMatch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public abstract class SQLDriver extends Driver<Long> {

	protected Connection con;
	protected Statement st;

	protected static final Map<String, String> EDGE_SOURCE_TYPES = ImmutableMap.of( //
			ModelConstants.SENSOR_EDGE, "id", //
			ModelConstants.DEFINED_BY, "Route_id", //
			ModelConstants.CONNECTSTO, "TrackElement_id");

	protected static final Map<String, String> EDGE_TARGET_TYPES = ImmutableMap.of( //
			ModelConstants.SENSOR_EDGE, "sensor", //
			ModelConstants.CONNECTSTO, "TrackElement_id_connectsTo");

	protected static final Map<String, String> EDGE_TABLE = ImmutableMap.of(
			//
			ModelConstants.ENTRY, ModelConstants.ROUTE, ModelConstants.SENSOR_EDGE, ModelConstants.TRACKELEMENT, ModelConstants.CONNECTSTO,
			ModelConstants.CONNECTSTO, ModelConstants.DEFINED_BY, ModelConstants.DEFINED_BY);

	@Override
	public String getExtension() {
		return ".sql";
	}

	@Override
	public List<SQLMatch> runQuery(final Query query, final String queryDefinition) throws IOException {
		final List<SQLMatch> results = new ArrayList<>();

		try (ResultSet rs = con.createStatement().executeQuery(queryDefinition)) {
			while (rs.next()) {
				final SQLMatch match = SQLMatch.createMatch(query, rs);
				results.add(match);
			}

		} catch (final SQLException e) {
			throw new IOException(e);
		}

		return results;
	}

	// create
	//
	// @Override
	// public void insertVertexWithEdge(final List<Long> sourceVertices, final String sourceVertexType, final String targetVertexType,
	// final String edgeType) throws IOException {
	// for (final Long sourceVertex : sourceVertices) {
	// insertVertexWithEdge(sourceVertex, sourceVertexType, targetVertexType, edgeType);
	// }
	// }
	//
	// @Override
	// public Long insertVertexWithEdge(final Long sourceVertex, final String sourceVertexType, final String targetVertexType,
	// final String edgeType) throws IOException {
	// long newVertexId = -1;
	// try {
	// final Statement st = con.createStatement();
	// st.executeUpdate(String.format("INSERT INTO `%s` VALUES ();", targetVertexType), Statement.RETURN_GENERATED_KEYS);
	//
	// try (ResultSet rs = st.getGeneratedKeys()) {
	// if (rs.next()) {
	// newVertexId = rs.getLong(1);
	//
	// String update;
	// if (SENSOR_EDGE.equals(edgeType)) {
	// update = String.format("UPDATE `%s` SET `%s` = %d WHERE `%s` = %d;", TRACKELEMENT, SENSOR_EDGE, newVertexId, ID,
	// sourceVertex);
	// } else {
	// update = String.format("INSERT INTO `%s` (`%s`, `%s`) VALUES (%d, %d);", edgeType, EDGE_SOURCE_TYPES.get(edgeType),
	// EDGE_TARGET_TYPES.get(edgeType), sourceVertex, newVertexId);
	// }
	// st.executeUpdate(update);
	// }
	// }
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	// return newVertexId;
	// }
	//
	// @Override
	// public void insertEdge(final Long sourceVertex, final String sourceVertexType, final Long targetVertex, final String edgeType)
	// throws IOException {
	//
	// try {
	// final Statement st = con.createStatement();
	// st.executeUpdate(String.format("INSERT INTO `%s` (`%s`, `%s`) VALUES (%d, %d);", EDGE_TABLE.get(edgeType),
	// EDGE_SOURCE_TYPES.get(edgeType), EDGE_TARGET_TYPES.get(edgeType), sourceVertex, targetVertex));
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	// }

	// read

	@Override
	public List<Long> collectVertices(final String type) throws IOException {
		final List<Long> results = new ArrayList<>();

		final String query = String.format("SELECT * FROM `%s`;", type);
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
	// }
	//
	// // update
	//
	// @Override
	// public void updateProperties(final List<Long> vertices, final String vertexType, final String propertyName,
	// final PropertyOperation attributeOperation) throws IOException {
	// try {
	// for (final Long vertex : vertices) {
	// final String update = String.format("UPDATE %s SET %s WHERE `%s` = %d;", vertexType,
	// attributeOperation.sqlUpdate(propertyName), ID, vertex);
	// con.createStatement().executeUpdate(update);
	// }
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	// }
	//
	// // delete
	//
	// @Override
	// public void deleteIncomingEdge(final List<Long> vertices, final String sourceVertexType, final String edgeType) throws IOException {
	// try {
	// for (final Long vertex : vertices) {
	// String update;
	// if (ModelConstants.SENSOR_EDGE.equals(edgeType)) {
	// update = String.format("UPDATE `%s` SET `%s` = NULL WHERE `%s` = %d", TRACKELEMENT, SENSOR_EDGE, SENSOR_EDGE, vertex);
	// } else {
	// update = String.format("DELETE FROM `%s` WHERE `%s` = %d;", edgeType, EDGE_TARGET_TYPES.get(edgeType), vertex);
	// }
	// con.createStatement().executeUpdate(update);
	// }
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	// }
	//
	// @Override
	// public void deleteAllOutgoingEdges(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
	// deleteOutgoingEdges(vertices, edgeType, true);
	// }
	//
	// @Override
	// public void deleteOneOutgoingEdge(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
	// deleteOutgoingEdges(vertices, edgeType, false);
	// }
	//
	// @Override
	// public void deleteSingleOutgoingEdge(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
	// try {
	// for (final Long vertex : vertices) {
	// String update;
	// if (SENSOR_EDGE.equals(edgeType)) {
	// update = String.format("UPDATE `%s` SET `%s` = NULL WHERE `%s` = %d;", TRACKELEMENT, edgeType, ID, vertex);
	// } else {
	// update = String.format("UPDATE `%s` SET `%s` = NULL WHERE `%s` = %d;", vertexType, edgeType, ID, vertex);
	// }
	// con.createStatement().executeUpdate(update);
	// }
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	// }
	//
	// protected void deleteOutgoingEdges(final List<Long> vertices, final String edgeType, final boolean all) throws IOException {
	// try {
	// for (final Long vertex : vertices) {
	// final String delete = String.format("DELETE FROM `%s` WHERE `%s` = %d" + (all ? "" : " LIMIT 1") + ";",
	// EDGE_TABLE.get(edgeType), EDGE_SOURCE_TYPES.get(edgeType), vertex);
	// con.createStatement().executeUpdate(delete);
	// }
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	// }
	//
	// @Override
	// public void deleteVertex(final Long vertex, final String vertexType) throws IOException {
	// try {
	// final Statement statement = con.createStatement();
	// statement.executeUpdate(String.format("DELETE FROM `%s` WHERE `%s` = %d;", vertexType, ID, vertex));
	// } catch (final SQLException e) {
	// throw new IOException(e);
	// }
	//
	// }
	//
	// @Override
	// public void deleteVertex(final Long vertex) throws IOException {
	// // TODO Auto-generated method stub
	//
	// }

}
