/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFTransformationUserConnectedSegments;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFTransformationUserPosLength;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFTransformationUserRouteSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFTransformationUserSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFTransformationUserSwitchSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFTransformationUserSwitchSet;

public abstract class EMFTransformation<O> extends Transformation<O> {

	public static EMFTransformation newInstance(final EMFDriver driver, final Query query, final Scenario scenario) {
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
		case USER:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new EMFTransformationUserConnectedSegments(driver);
			case POSLENGTH:
				return new EMFTransformationUserPosLength(driver);
			case ROUTESENSOR:
				return new EMFTransformationUserRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new EMFTransformationUserSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new EMFTransformationUserSwitchSensor(driver);
			case SWITCHSET:
				return new EMFTransformationUserSwitchSet(driver);
			default:
				break;
			}
		}
		throw new UnsupportedOperationException("Query " + query + " not supported");
	}

}
