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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user.SesameTransformationUserPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user.SesameTransformationUserRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user.SesameTransformationUserSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user.SesameTransformationUserSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user.SesameTransformationUserSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class SesameTransformation<M> extends Transformation<M> {

	protected SesameDriver sesameDriver;

	protected SesameTransformation(final SesameDriver sesameDriver) {
		this.sesameDriver = sesameDriver;
	}

	public static Transformation<?> newInstance(final SesameDriver sesameDriver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case POSLENGTH:
				return new SesameTransformationRepairPosLength(sesameDriver);
			case ROUTESENSOR:
				return new SesameTransformationRepairRouteSensor(sesameDriver);
			case SEMAPHORENEIGHBOR:
				return new SesameTransformationRepairSemaphoreNeighbor(sesameDriver);
			case SWITCHSENSOR:
				return new SesameTransformationRepairSwitchSensor(sesameDriver);
			case SWITCHSET:
				return new SesameTransformationRepairSwitchSet(sesameDriver);
			default:
				break;
			}
		case USER:
			switch (query) {
			case POSLENGTH:
				return new SesameTransformationUserPosLength(sesameDriver);
			case ROUTESENSOR:
				return new SesameTransformationUserRouteSensor(sesameDriver);
			case SEMAPHORENEIGHBOR:
				return new SesameTransformationUserSemaphoreNeighbor(sesameDriver);
			case SWITCHSENSOR:
				return new SesameTransformationUserSwitchSensor(sesameDriver);
			case SWITCHSET:
				return new SesameTransformationUserSwitchSet(sesameDriver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
