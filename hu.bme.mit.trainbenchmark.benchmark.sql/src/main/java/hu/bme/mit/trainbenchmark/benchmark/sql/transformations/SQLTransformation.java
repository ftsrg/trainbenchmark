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

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInject;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SQLTransformation<TObject> extends ModelTransformation<TObject, SQLDriver> {

	protected PreparedStatement preparedUpdateStatement;
	protected BenchmarkConfig benchmarkConfig;
	protected String updateQuery;
		
	protected SQLTransformation(final SQLDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		super(driver);
		this.benchmarkConfig = benchmarkConfig;

		final String updatePath = getTransformationDirectory() + benchmarkConfig.getScenarioName() + query + ".sql";
		updateQuery = FileUtils.readFileToString(new File(updatePath));
	}
	
	protected String getTransformationDirectory() {
		return benchmarkConfig.getWorkspacePath() + driver.getResourceDirectory() + "transformations/";
	}

	public static ModelTransformation<?, ?> newInstance(final SQLDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		switch (benchmarkConfig.getScenario()) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLTransformationRepairConnectedSegments(driver, benchmarkConfig, query);				
			case POSLENGTH:
				return new SQLTransformationRepairPosLength(driver, benchmarkConfig, query);
			case ROUTESENSOR:
				return new SQLTransformationRepairRouteSensor(driver, benchmarkConfig, query);
			case SEMAPHORENEIGHBOR:
				return new SQLTransformationRepairSemaphoreNeighbor(driver, benchmarkConfig, query);
			case SWITCHSENSOR:
				return new SQLTransformationRepairSwitchSensor(driver, benchmarkConfig, query);
			case SWITCHSET:
				return new SQLTransformationRepairSwitchSet(driver, benchmarkConfig, query);
			default:
				break;
			}
		case INJECT:
			return new SQLTransformationInject(driver, benchmarkConfig, query);				
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query + ", scenario: " + benchmarkConfig.getScenario());
	}
}
