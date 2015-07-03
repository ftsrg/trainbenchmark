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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class SQLTransformationInjectConnectedSegments extends SQLTransformationInject {

	public SQLTransformationInjectConnectedSegments(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> segments) throws IOException {
		final Connection connection = sqlDriver.getConnection();

		for (final Long segment1 : segments) {
			try {
				// get (segment3) node
				final String querySegment3 = String.format("" + //
						"SELECT `TrackElement2` " + //
						"FROM `%s` " + //
						"WHERE `TrackElement1` = %s;", CONNECTSTO, segment1);
				final ResultSet resultSetSegment3 = connection.createStatement().executeQuery(querySegment3);
				if (!resultSetSegment3.next()) {
					continue;
				}
				final long segment3 = resultSetSegment3.getLong(1);

				// get (sensor) node
				final String querySensor = String.format("" + //
						"SELECT `%s` " + //
						"FROM `%s` " + //
						"WHERE `%s` = %s;", SENSOR_EDGE, TRACKELEMENT, ID, segment1);
				final ResultSet resultSetSensor = connection.createStatement().executeQuery(querySensor);
				if (!resultSetSensor.next()) {
					continue;
				}
				final long sensor = resultSetSensor.getLong(1);

				// delete (segment1)-[:connectsTo]->(segment2) edge
				final String deleteConnectsTo0 = String.format("" + //
						"DELETE FROM `%s` " + //
						"WHERE `TrackElement1` = %d;", //
						CONNECTSTO, segment1);
				connection.createStatement().executeUpdate(deleteConnectsTo0);

				// insert new segment as a TrackElement and retrieve its id
				// also insert the (segment2)-[:sensor]->(sensor) edge
				final String insertSegment2TrackElement = String.format("" + //
						"INSERT INTO `%s` (`%s`) " + //
						"VALUES (%d);", //
						TRACKELEMENT, SENSOR_EDGE, //
						sensor);
				final Statement statement = connection.createStatement();
				statement.executeUpdate(insertSegment2TrackElement, Statement.RETURN_GENERATED_KEYS);

				try (ResultSet rs3 = statement.getGeneratedKeys()) {
					if (rs3.next()) {
						// get the id of the new vertex
						final long segment2 = rs3.getLong(1);

						// insert (segment2) node
						final String insertSegment2 = String.format("" + //
								"INSERT INTO `%s` (`%s`) " + //
								"VALUES (%d);", //
								SEGMENT, ID, //
								segment2);
						// insert (segment1)-[:connectsTo]->(segment3) edge
						final String insertConnectsTo1 = String.format("" + //
								"INSERT INTO `%s` (`TrackElement1`, `TrackElement2`) " + //
								"VALUES (%d, %d);", //
								CONNECTSTO, segment1, segment2);
						// insert (segment1)-[:connectsTo]->(segment3) edge
						final String insertConnectsTo2 = String.format("" + //
								"INSERT INTO `%s` (`TrackElement1`, `TrackElement2`) " + //
								"VALUES (%d, %d);", //
								CONNECTSTO, segment2, segment3);
						connection.createStatement().executeUpdate(insertSegment2);
						connection.createStatement().executeUpdate(insertConnectsTo1);
						connection.createStatement().executeUpdate(insertConnectsTo2);
					}
				}
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}
}
