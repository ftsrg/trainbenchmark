/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLSwitchSensorMatch;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class SQLTransformationRepairSwitchSensor extends SQLTransformationRepair<SQLSwitchSensorMatch> {

	public SQLTransformationRepairSwitchSensor(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<SQLSwitchSensorMatch> matches) throws IOException {
		for (final SQLSwitchSensorMatch match : matches) {
			try {
				final String create = String.format("INSERT INTO `%s` VALUES ();", SENSOR);
				final Statement statement = sqlDriver.getConnection().createStatement();
				statement.executeUpdate(create, Statement.RETURN_GENERATED_KEYS);

				try (ResultSet rs = statement.getGeneratedKeys()) {
					if (rs.next()) {
						// get the id of the new vertex
						final long newVertexId = rs.getLong(1);

						String update;
						update = String.format("UPDATE `%s` SET `%s` = %d WHERE `%s` = %d;", TRACKELEMENT, SENSOR_EDGE, newVertexId, ID,
								match.getSw());

						statement.executeUpdate(update);
					}
				}
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}

}
