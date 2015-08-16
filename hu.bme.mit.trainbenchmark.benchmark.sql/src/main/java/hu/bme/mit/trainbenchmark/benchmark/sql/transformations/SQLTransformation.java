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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInject;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;

import org.apache.commons.io.FileUtils;

public abstract class SQLTransformation<M> extends Transformation<M> {

	protected PreparedStatement preparedUpdateStatement;
	protected SQLDriver sqlDriver;
	protected BenchmarkConfig bc;
	protected final String updateQuery;

	protected SQLTransformation(final SQLDriver sqlDriver, final Query query,
			final ScenarioConstants scenario) throws IOException {
		this.sqlDriver = sqlDriver;

		final String updatePath = getTransformationDirectory() + scenario + query + ".sql";
		updateQuery = FileUtils.readFileToString(new File(updatePath));
	}

	protected String getTransformationDirectory() {
		return bc.getWorkspacePath() + sqlDriver.getResourceDirectory() + "transformations/";
	}

	public static Transformation<?> newInstance(final SQLDriver sqlDriver, final Query query,
			final ScenarioConstants scenario) throws IOException {
		if (!hasTransformation(scenario)) {
			return null;
		}
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLTransformationRepairConnectedSegments(sqlDriver, query,
						scenario);
			case POSLENGTH:
				return new SQLTransformationRepairPosLength(sqlDriver, query, scenario);
			case ROUTESENSOR:
				return new SQLTransformationRepairRouteSensor(sqlDriver, query, scenario);
			case SEMAPHORENEIGHBOR:
				return new SQLTransformationRepairSemaphoreNeighbor(sqlDriver, query,
						scenario);
			case SWITCHSENSOR:
				return new SQLTransformationRepairSwitchSensor(sqlDriver, query, scenario);
			case SWITCHSET:
				return new SQLTransformationRepairSwitchSet(sqlDriver, query, scenario);
			default:
				break;
			}
		case INJECT:
			return new SQLTransformationInject(sqlDriver, query, scenario);
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query + ", scenario: " + scenario);
	}
}
