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

public abstract class OrientDbTransformation<TObject> extends Transformation<TObject, OrientDbDriver> {

	protected OrientDbTransformation(final OrientDbDriver driver) {
		super(driver);
	}

	public static Transformation<?, ?> newInstance(final OrientDbDriver driver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new OrientDbTransformationRepairConnectedSegments(driver);
			case POSLENGTH:
				return new OrientDbTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new OrientDbTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new OrientDbTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new OrientDbTransformationRepairSwitchSensor(driver);
			case SWITCHSET:
				return new OrientDbTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new OrientDbTransformationInjectConnectedSegments(driver);
			case POSLENGTH:
				return new OrientDbTransformationInjectPosLength(driver);
			case ROUTESENSOR:
				return new OrientDbTransformationInjectRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new OrientDbTransformationInjectSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new OrientDbTransformationInjectSwitchSensor(driver);
			case SWITCHSET:
				return new OrientDbTransformationInjectSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
	
}
