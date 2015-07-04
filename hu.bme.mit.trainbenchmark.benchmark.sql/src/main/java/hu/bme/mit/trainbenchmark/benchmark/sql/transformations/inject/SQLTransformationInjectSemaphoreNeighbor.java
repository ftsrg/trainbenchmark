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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class SQLTransformationInjectSemaphoreNeighbor extends SQLTransformationInject {

	protected final String updateQuery;
	protected PreparedStatement preparedUpdateStatement;

	public SQLTransformationInjectSemaphoreNeighbor(final SQLDriver sqlDriver, final BenchmarkConfig bc) throws IOException {
		super(sqlDriver);
		
		final String updatePath = getTransformationDirectory(bc) + "InjectSemaphoreNeighbor.sql";
		updateQuery = FileUtils.readFileToString(new File(updatePath));
	}

	@Override
	public void rhs(final Collection<Long> routes) throws SQLException {
		for (final Long route : routes) {
			// (route)-[:entry]->(semaphore) edge
			if (preparedUpdateStatement == null) {
				preparedUpdateStatement = sqlDriver.getConnection().prepareStatement(updateQuery);
			}

			preparedUpdateStatement.setLong(1, route);
			preparedUpdateStatement.executeUpdate();
		}
	}

}
