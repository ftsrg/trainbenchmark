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
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SQLTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user.SQLTransformationUserConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user.SQLTransformationUserPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user.SQLTransformationUserRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user.SQLTransformationUserSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user.SQLTransformationUserSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.user.SQLTransformationUserSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;

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
		case USER:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLTransformationUserConnectedSegments(sqlDriver);				
			case POSLENGTH:
				return new SQLTransformationUserPosLength(sqlDriver);
			case ROUTESENSOR:
				return new SQLTransformationUserRouteSensor(sqlDriver);
			case SEMAPHORENEIGHBOR:
				return new SQLTransformationUserSemaphoreNeighbor(sqlDriver);
			case SWITCHSENSOR:
				return new SQLTransformationUserSwitchSensor(sqlDriver);
			case SWITCHSET:
				return new SQLTransformationUserSwitchSet(sqlDriver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
