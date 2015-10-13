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

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.hawk.config.HawkBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.hawk.driver.HawkDriver;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class HawkTransformation<TObject> extends Transformation<TObject, HawkDriver<? extends BasePatternMatch>> {

	public HawkTransformation(final HawkDriver<?> driver) {
		super(driver);
	}

	public static Transformation<?, ?> newInstance(final HawkDriver<?> driver, final Query query,
			final Scenario scenario, HawkBenchmarkConfig benchmarkConfig) {
		return new HawkPreparedTransformation(driver, query, scenario, benchmarkConfig);
		
		// switch (scenario) {
		// case REPAIR:
		// switch (query) {
		// case CONNECTEDSEGMENTS:
		// return new HawkTransformationRepairConnectedSegments(driver);
		// case POSLENGTH:
		// return new HawkTransformationRepairPosLength(driver);
		// case ROUTESENSOR:
		// return new HawkTransformationRepairRouteSensor(driver);
		// case SEMAPHORENEIGHBOR:
		// return new HawkTransformationRepairSemaphoreNeighbor(driver);
		// case SWITCHSENSOR:
		// return new HawkTransformationRepairSwitchSensor(driver);
		// case SWITCHSET:
		// return new HawkTransformationRepairSwitchSet(driver);
		// default:
		// break;
		// }
		// case INJECT:
		// switch (query) {
		// case CONNECTEDSEGMENTS:
		// return new HawkTransformationInjectConnectedSegments(driver);
		// case POSLENGTH:
		// return new HawkTransformationInjectPosLength(driver);
		// case ROUTESENSOR:
		// return new HawkTransformationInjectRouteSensor(driver);
		// case SEMAPHORENEIGHBOR:
		// return new HawkTransformationInjectSemaphoreNeighbor(driver);
		// case SWITCHSENSOR:
		// return new HawkTransformationInjectSwitchSensor(driver);
		// case SWITCHSET:
		// return new HawkTransformationInjectSwitchSet(driver);
		// default:
		// break;
		// }
		// default:
		// break;
		// }
		// throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
