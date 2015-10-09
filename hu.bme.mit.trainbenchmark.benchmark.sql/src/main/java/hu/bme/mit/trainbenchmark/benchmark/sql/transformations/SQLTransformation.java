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

public abstract class SQLTransformation<TObject> extends Transformation<TObject, SQLDriver> {

	protected PreparedStatement preparedUpdateStatement;
	protected BenchmarkConfig bc;
	protected final String updateQuery;
		
	protected SQLTransformation(final SQLDriver driver, final BenchmarkConfig bc) throws IOException {
		super(driver);
		this.bc = bc;

		final String updatePath = getTransformationDirectory() + bc.getScenarioName() + bc.getQuery() + ".sql";
		updateQuery = FileUtils.readFileToString(new File(updatePath));
	}
	
	protected String getTransformationDirectory() {
		return bc.getWorkspacePath() + driver.getResourceDirectory() + "transformations/";
	}

	public static Transformation<?, ?> newInstance(final SQLDriver driver, final BenchmarkConfig bc) throws IOException {
		switch (bc.getScenario()) {
		case REPAIR:
			switch (bc.getQuery()) {
			case CONNECTEDSEGMENTS:
				return new SQLTransformationRepairConnectedSegments(driver, bc);				
			case POSLENGTH:
				return new SQLTransformationRepairPosLength(driver, bc);
			case ROUTESENSOR:
				return new SQLTransformationRepairRouteSensor(driver, bc);
			case SEMAPHORENEIGHBOR:
				return new SQLTransformationRepairSemaphoreNeighbor(driver, bc);
			case SWITCHSENSOR:
				return new SQLTransformationRepairSwitchSensor(driver, bc);
			case SWITCHSET:
				return new SQLTransformationRepairSwitchSet(driver, bc);
			default:
				break;
			}
		case INJECT:
			return new SQLTransformationInject(driver, bc);				
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + bc.getQuery() + ", scenario: " + bc.getScenario());
	}
}
