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
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.repair.EMFTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationPosLength;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationRouteSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationSwitchSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationSwitchSet;

public abstract class EMFTransformation<O> extends Transformation<O> {

	public static EMFTransformation newInstance(final EMFDriver driver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
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
			case POSLENGTH:
				return new EMFUserTransformationPosLength(driver);
			case ROUTESENSOR:
				return new EMFUserTransformationRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new EMFUserTransformationSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new EMFUserTransformationSwitchSensor(driver);
			case SWITCHSET:
				return new EMFUserTransformationSwitchSet(driver);
			default:
				break;
			}
		}
		throw new UnsupportedOperationException("Query " + query + " not supported");
	}

}
