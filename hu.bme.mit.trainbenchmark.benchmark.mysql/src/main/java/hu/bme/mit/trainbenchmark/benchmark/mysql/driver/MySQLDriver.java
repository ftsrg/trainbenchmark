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
import hu.bme.mit.trainbenchmark.benchmark.mysql.MySQLProcess;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ImmutableMap;

public class MySQLDriver extends DatabaseDriver<Long> {

	protected final String url = "jdbc:mysql://localhost:3306/trainbenchmark";
	protected final String user = "root";
	protected final String password = "";

	protected final String query;

	protected Connection con;
	protected Statement st;

	protected static final Map<String, String> EDGE_SOURCE_TYPES = ImmutableMap.of(
			ModelConstants.TRACKELEMENT_SENSOR, "TrackElement_id",
			ModelConstants.ROUTE_ROUTEDEFINITION, "Route_id", 
			ModelConstants.TRACKELEMENT_CONNECTSTO, "TrackElement_id");

	protected static final Map<String, String> EDGE_TARGET_TYPES = ImmutableMap.of(
			ModelConstants.TRACKELEMENT_SENSOR, "Sensor_id",
			ModelConstants.TRACKELEMENT_CONNECTSTO, "TrackElement_id_connectsTo");

	protected static final Map<String, String> EDGE_TABLE = ImmutableMap.of(ModelConstants.ROUTE_ENTRY, ModelConstants.ROUTE);

	public MySQLDriver(final String queryPath) throws IOException {
		query = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public void read(final String modelPath) throws IOException {
		final Runtime rt = Runtime.getRuntime();
		final String[] command = { "/bin/bash", "-c", "mysql -u " + user + " < " + modelPath };

		try {
			final Process pr = rt.exec(command);
			pr.waitFor();
		} catch (final Exception e) {
			throw new IOException(e);
		}

		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<Long> runQuery() throws IOException {
		final List<Long> results = new ArrayList<>();

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
	public Comparator<Long> getComparator() {
		// a null comparator provides natural ordering
		return null;
	}

	@Override
	public void destroy() throws IOException {
		try {
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
		MySQLProcess.stopSQLProcess();
	}

	// create

	@Override
	public void insertVertexWithEdge(final List<Long> sourceVertices, final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		for (final Long sourceVertex : sourceVertices) {
			insertVertexWithEdge(sourceVertex, sourceVertexType, targetVertexType, edgeType);
		}
	}

	@Override
	public Long insertVertexWithEdge(final Long sourceVertex,
			final String sourceVertexType, final String targetVertexType, final String edgeType)
			throws IOException {
		long newVertexId = -1;
		try {
			final Statement st = con.createStatement();
			st.executeUpdate(String.format("INSERT INTO `%s` VALUES ();", targetVertexType), Statement.RETURN_GENERATED_KEYS);

			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					newVertexId = rs.getLong(1);
					st.executeUpdate(String.format("INSERT INTO `%s` (`%s`, `%s`) VALUES (%d, %d);", edgeType,
							EDGE_SOURCE_TYPES.get(edgeType), EDGE_TARGET_TYPES.get(edgeType),sourceVertex, newVertexId));
				}
			}
		}
		catch(final SQLException e){
			throw new IOException(e);
		}
		return newVertexId;
	}

	@Override
	public void insertEdge(Long sourceVertex,final String sourceVertexType, Long targetVertex, String edgeType) throws IOException {
		
		try {
			final Statement st = con.createStatement();
			st.executeUpdate(String.format("INSERT INTO `%s` (`%s`, `%s`) VALUES (%d, %d);", edgeType, 
					EDGE_SOURCE_TYPES.get(edgeType), EDGE_TARGET_TYPES.get(edgeType), sourceVertex, targetVertex));
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	// read

	@Override
	public List<Long> collectVertices(final String type) throws IOException {
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
	public List<Long> collectOutgoingConnectedVertices(final Long sourceVertex,final String sourceVertexType, 
			final String targetVertexType, final String edgeType) throws IOException {
		final List<Long> results = new ArrayList<>();
		
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(String.format("SELECT * FROM %s WHERE %s = %s;", edgeType, 
					EDGE_SOURCE_TYPES.get(edgeType), sourceVertex));
			while (rs.next()){
				results.add(rs.getLong(2));
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return results;
	}

	// update

	@Override
	public void updateProperties(final List<Long> vertices, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) throws IOException {
		try {
			for (final Long vertex : vertices) {
				final String update = String.format("UPDATE %s SET %s WHERE id = %d;", vertexType,
						attributeOperation.sqlUpdate(propertyName), vertex);
				con.createStatement().executeUpdate(update);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	// delete

	@Override
	public void deleteAllIncomingEdges(final List<Long> vertices, final String sourceVertexType, final String edgeType) throws IOException {
		try {
			for (final Long vertex : vertices) {
				final String update = String.format("DELETE FROM %s WHERE %s = %d;", edgeType, EDGE_TARGET_TYPES.get(edgeType), vertex);
				con.createStatement().executeUpdate(update);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deleteAllOutgoingEdges(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteOutgoingEdges(vertices, edgeType, true);
	}

	@Override
	public void deleteOneOutgoingEdge(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
		deleteOutgoingEdges(vertices, edgeType, false);
	}

	@Override
	public void deleteSingleOutgoingEdge(final List<Long> vertices, final String vertexType, final String edgeType) throws IOException {
		try {
			for (final Long vertex : vertices) {
				final String update = String.format("UPDATE `%s` SET `%s` = 0 WHERE id = %d;", vertexType, edgeType, vertex);
				con.createStatement().executeUpdate(update);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	protected void deleteOutgoingEdges(final List<Long> vertices, final String edgeType, final boolean all) throws IOException {
		try {
			for (final Long vertex : vertices) {
				final String delete = String.format("DELETE FROM %s WHERE %s = %d" + (all ? "" : " LIMIT 1") + ";", edgeType,
						EDGE_SOURCE_TYPES.get(edgeType), vertex);
				con.createStatement().executeUpdate(delete);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deleteVertex(final Long vertex, final String vertexType) throws IOException {
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(String.format("DELETE FROM %s WHERE id = %d;", vertexType, vertex));
		} catch (SQLException e) {
			throw new IOException(e);
		}
		
	}
}
