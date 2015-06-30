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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject.OrientDbTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject.OrientDbTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject.OrientDbTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject.OrientDbTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject.OrientDbTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject.OrientDbTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class OrientDbTransformation<M> extends Transformation<M> {

	protected OrientDbDriver orientDriver;

	protected OrientDbTransformation(final OrientDbDriver orientDriver) {
		this.orientDriver = orientDriver;
	}

	public static Transformation<?> newInstance(final OrientDbDriver orientDriver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new OrientDbTransformationRepairConnectedSegments(orientDriver);
			case POSLENGTH:
				return new OrientDbTransformationRepairPosLength(orientDriver);
			case ROUTESENSOR:
				return new OrientDbTransformationRepairRouteSensor(orientDriver);
			case SEMAPHORENEIGHBOR:
				return new OrientDbTransformationRepairSemaphoreNeighbor(orientDriver);
			case SWITCHSENSOR:
				return new OrientDbTransformationRepairSwitchSensor(orientDriver);
			case SWITCHSET:
				return new OrientDbTransformationRepairSwitchSet(orientDriver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new OrientDbTransformationInjectConnectedSegments(orientDriver);
			case POSLENGTH:
				return new OrientDbTransformationInjectPosLength(orientDriver);
			case ROUTESENSOR:
				return new OrientDbTransformationInjectRouteSensor(orientDriver);
			case SEMAPHORENEIGHBOR:
				return new OrientDbTransformationInjectSemaphoreNeighbor(orientDriver);
			case SWITCHSENSOR:
				return new OrientDbTransformationInjectSwitchSensor(orientDriver);
			case SWITCHSET:
				return new OrientDbTransformationInjectSwitchSet(orientDriver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
	
}
