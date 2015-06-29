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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SQLTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class SQLTransformation<M> extends Transformation<M> {

	protected SQLDriver sqlDriver;

	protected SQLTransformation(final SQLDriver sqlDriver) {
		this.sqlDriver = sqlDriver;
	}

	public static Transformation<?> newInstance(final SQLDriver sqlDriver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLTransformationRepairConnectedSegments(sqlDriver);				
			case POSLENGTH:
				return new SQLTransformationRepairPosLength(sqlDriver);
			case ROUTESENSOR:
				return new SQLTransformationRepairRouteSensor(sqlDriver);
			case SEMAPHORENEIGHBOR:
				return new SQLTransformationRepairSemaphoreNeighbor(sqlDriver);
			case SWITCHSENSOR:
				return new SQLTransformationRepairSwitchSensor(sqlDriver);
			case SWITCHSET:
				return new SQLTransformationRepairSwitchSet(sqlDriver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLTransformationInjectConnectedSegments(sqlDriver);				
			case POSLENGTH:
				return new SQLTransformationInjectPosLength(sqlDriver);
			case ROUTESENSOR:
				return new SQLTransformationInjectRouteSensor(sqlDriver);
			case SEMAPHORENEIGHBOR:
				return new SQLTransformationInjectSemaphoreNeighbor(sqlDriver);
			case SWITCHSENSOR:
				return new SQLTransformationInjectSwitchSensor(sqlDriver);
			case SWITCHSET:
				return new SQLTransformationInjectSwitchSet(sqlDriver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
