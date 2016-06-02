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
package hu.bme.mit.trainbenchmark.benchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EMFTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EMFTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EMFTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EMFTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EMFTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EMFTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFTransformation<TObject, TDriver extends EMFDriver> extends Transformation<TObject, TDriver> {

	public EMFTransformation(TDriver driver) {
		super(driver);
	}

	public static EMFTransformation<?, ?> newInstance(final EMFDriver driver, final RailwayQuery query, final ScenarioEnum scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new EMFTransformationRepairConnectedSegments(driver);
			case POSLENGTH:
				return new EMFTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new EMFTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new EMFTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new EMFTransformationRepairSwitchSensor(driver);
			case SWITCHSET:
				return new EMFTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new EMFTransformationInjectConnectedSegments(driver);
			case POSLENGTH:
				return new EMFTransformationInjectPosLength(driver);
			case ROUTESENSOR:
				return new EMFTransformationInjectRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new EMFTransformationInjectSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new EMFTransformationInjectSwitchSensor(driver);
			case SWITCHSET:
				return new EMFTransformationInjectSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query " + query + " not supported");
	}

}
