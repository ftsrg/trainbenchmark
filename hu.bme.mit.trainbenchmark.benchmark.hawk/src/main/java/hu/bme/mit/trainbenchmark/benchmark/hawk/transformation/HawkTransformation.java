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
package hu.bme.mit.trainbenchmark.benchmark.hawk.transformation;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.inject.HawkTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.inject.HawkTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.inject.HawkTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.inject.HawkTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.inject.HawkTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.inject.HawkTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.repair.HawkTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.repair.HawkTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.repair.HawkTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.repair.HawkTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.repair.HawkTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.hawk.transformation.repair.HawkTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class HawkTransformation<M> extends Transformation<M> {

	protected final HawkDriver<?> driver;

	public HawkTransformation(final HawkDriver<?> driver) {
		super();
		this.driver = driver;
	}

	public static Transformation<?> newInstance(final HawkDriver<?> driver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new HawkTransformationRepairConnectedSegments(driver);
			case POSLENGTH:
				return new HawkTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new HawkTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new HawkTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new HawkTransformationRepairSwitchSensor(driver);
			case SWITCHSET:
				return new HawkTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new HawkTransformationInjectConnectedSegments(driver);
			case POSLENGTH:
				return new HawkTransformationInjectPosLength(driver);
			case ROUTESENSOR:
				return new HawkTransformationInjectRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new HawkTransformationInjectSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new HawkTransformationInjectSwitchSensor(driver);
			case SWITCHSET:
				return new HawkTransformationInjectSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
