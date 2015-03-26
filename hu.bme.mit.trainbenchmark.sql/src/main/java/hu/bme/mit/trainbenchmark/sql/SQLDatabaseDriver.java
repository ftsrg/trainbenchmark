package hu.bme.mit.trainbenchmark.sql;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PropertyOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ImmutableMap;

public abstract class SQLDatabaseDriver extends DatabaseDriver<Long> {
	protected final String query;

	protected Connection con;
	protected Statement st;

	protected static final Map<String, String> EDGE_SOURCE_TYPES = ImmutableMap
			.of( //
			ModelConstants.SENSOR_EDGE, "id", //
					ModelConstants.DEFINED_BY, "Route_id", //
					ModelConstants.CONNECTSTO, "TrackElement_id");

	protected static final Map<String, String> EDGE_TARGET_TYPES = ImmutableMap
			.of( //
			ModelConstants.SENSOR_EDGE, "Sensor_id", //
					ModelConstants.CONNECTSTO, "TrackElement_id_connectsTo");

	protected static final Map<String, String> EDGE_TABLE = ImmutableMap.of( //
			ModelConstants.ENTRY, ModelConstants.ROUTE //
			);

	public SQLDatabaseDriver(final String queryPath) throws IOException {
		query = FileUtils.readFileToString(new File(queryPath));
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

	// create

	@Override
	public void insertVertexWithEdge(final List<Long> sourceVertices,
			final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		for (final Long sourceVertex : sourceVertices) {
			insertVertexWithEdge(sourceVertex, sourceVertexType,
					targetVertexType, edgeType);
		}
	}

	@Override
	public Long insertVertexWithEdge(final Long sourceVertex,
			final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		long newVertexId = -1;
		try {
			final Statement st = con.createStatement();
			st.executeUpdate(String.format("INSERT INTO `%s` VALUES ();",
					targetVertexType), Statement.RETURN_GENERATED_KEYS);

			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					newVertexId = rs.getLong(1);

					String update;
					if (SENSOR_EDGE.equals(edgeType)) {
						update = String.format(
								"UPDATE `%s` SET `%s` = %d WHERE `%s` = %d;",
								TRACKELEMENT, SENSOR_EDGE, newVertexId, ID,
								sourceVertex);
					} else {
						update = String
								.format("INSERT INTO `%s` (`%s`, `%s`) VALUES (%d, %d);",
										edgeType,
										EDGE_SOURCE_TYPES.get(edgeType),
										EDGE_TARGET_TYPES.get(edgeType),
										sourceVertex, newVertexId);
					}
					st.executeUpdate(update);
				}
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
		return newVertexId;
	}

	@Override
	public void insertEdge(final Long sourceVertex,
			final String sourceVertexType, final Long targetVertex,
			final String edgeType) throws IOException {

		try {
			final Statement st = con.createStatement();
			st.executeUpdate(String.format(
					"INSERT INTO `%s` (`%s`, `%s`) VALUES (%d, %d);", edgeType,
					EDGE_SOURCE_TYPES.get(edgeType),
					EDGE_TARGET_TYPES.get(edgeType), sourceVertex, targetVertex));
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

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
	public List<Long> collectOutgoingConnectedVertices(final Long sourceVertex,
			final String sourceVertexType, final String targetVertexType,
			final String edgeType) throws IOException {
		final List<Long> results = new ArrayList<>();

		try {
			final Statement statement = con.createStatement();
			final ResultSet rs = statement.executeQuery(String.format(
					"SELECT * FROM `%s` WHERE `%s` = %s;", edgeType,
					EDGE_SOURCE_TYPES.get(edgeType), sourceVertex));
			while (rs.next()) {
				results.add(rs.getLong(2));
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
		return results;
	}

	// update

	@Override
	public void updateProperties(final List<Long> vertices,
			final String vertexType, final String propertyName,
			final PropertyOperation attributeOperation) throws IOException {
		try {
			for (final Long vertex : vertices) {
				final String update = String.format(
						"UPDATE %s SET %s WHERE `%s` = %d;", vertexType,
						attributeOperation.sqlUpdate(propertyName), ID, vertex);
				con.createStatement().executeUpdate(update);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	// delete

	@Override
	public void deleteIncomingEdge(final List<Long> vertices,
			final String sourceVertexType, final String edgeType)
			throws IOException {
		try {
			for (final Long vertex : vertices) {
				String update;
				if (ModelConstants.SENSOR_EDGE.equals(edgeType)) {
					update = String.format(
							"UPDATE `%s` SET `%s` = NULL WHERE `%s` = %d",
							TRACKELEMENT, SENSOR_EDGE, SENSOR_EDGE, vertex);
				} else {
					update = String.format("DELETE FROM `%s` WHERE `%s` = %d;",
							edgeType, EDGE_TARGET_TYPES.get(edgeType), vertex);
				}
				con.createStatement().executeUpdate(update);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deleteAllOutgoingEdges(final List<Long> vertices,
			final String vertexType, final String edgeType) throws IOException {
		deleteOutgoingEdges(vertices, edgeType, true);
	}

	@Override
	public void deleteOneOutgoingEdge(final List<Long> vertices,
			final String vertexType, final String edgeType) throws IOException {
		deleteOutgoingEdges(vertices, edgeType, false);
	}

	@Override
	public void deleteSingleOutgoingEdge(final List<Long> vertices,
			final String vertexType, final String edgeType) throws IOException {
		try {
			for (final Long vertex : vertices) {
				String update;
				if (SENSOR_EDGE.equals(edgeType)) {
					update = String.format(
							"UPDATE `%s` SET `%s` = NULL WHERE `%s` = %d;",
							TRACKELEMENT, edgeType, ID, vertex);
				} else {
					update = String.format(
							"UPDATE `%s` SET `%s` = NULL WHERE `%s` = %d;",
							vertexType, edgeType, ID, vertex);
				}
				con.createStatement().executeUpdate(update);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	protected void deleteOutgoingEdges(final List<Long> vertices,
			final String edgeType, final boolean all) throws IOException {
		try {
			for (final Long vertex : vertices) {
				final String delete = String.format(
						"DELETE FROM `%s` WHERE `%s` = %d"
								+ (all ? "" : " LIMIT 1") + ";", edgeType,
						EDGE_SOURCE_TYPES.get(edgeType), vertex);
				con.createStatement().executeUpdate(delete);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void deleteVertex(final Long vertex, final String vertexType)
			throws IOException {
		try {
			final Statement statement = con.createStatement();
			statement.executeUpdate(String
					.format("DELETE FROM `%s` WHERE `%s` = %d;", vertexType,
							ID, vertex));
		} catch (final SQLException e) {
			throw new IOException(e);
		}

	}

	@Override
	public void deleteVertex(final Long vertex) throws IOException {
		// TODO Auto-generated method stub

	}
}
