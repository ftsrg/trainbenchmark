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
package hu.bme.mit.trainbenchmark.emf.transformation;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.inject.EMFTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.emf.transformation.inject.EMFTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.emf.transformation.inject.EMFTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.inject.EMFTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.emf.transformation.inject.EMFTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.inject.EMFTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSwitchSet;

public abstract class EMFTransformation<O> extends Transformation<O> {

	public static EMFTransformation newInstance(final EMFDriver driver, final Query query, final ScenarioConstants scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new EMFTransformationRepairConnectedSegments();
			case POSLENGTH:
				return new EMFTransformationRepairPosLength();
			case ROUTESENSOR:
				return new EMFTransformationRepairRouteSensor();
			case SEMAPHORENEIGHBOR:
				return new EMFTransformationRepairSemaphoreNeighbor();
			case SWITCHSENSOR:
				return new EMFTransformationRepairSwitchSensor();
			case SWITCHSET:
				return new EMFTransformationRepairSwitchSet();
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
		}
		throw new UnsupportedOperationException("Query " + query + " not supported");
	}

}
