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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.inject.TinkerGraphTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.inject.TinkerGraphTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.inject.TinkerGraphTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.inject.TinkerGraphTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.inject.TinkerGraphTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.inject.TinkerGraphTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public abstract class TinkerGraphTransformation<TObject> extends Transformation<TObject, TinkerGraphDriver> {

	protected TinkerGraphTransformation(final TinkerGraphDriver driver) {
		super(driver);
	}

	public static Transformation<?, ?> newInstance(final TinkerGraphDriver driver, final RailwayQuery query, final ScenarioEnum scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new TinkerGraphTransformationRepairConnectedSegments(driver);				
			case POSLENGTH:
				return new TinkerGraphTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new TinkerGraphTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new TinkerGraphTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new TinkerGraphTransformationRepairSwitchSensor(driver);
			case SWITCHSET:
				return new TinkerGraphTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new TinkerGraphTransformationInjectConnectedSegments(driver);				
			case POSLENGTH:
				return new TinkerGraphTransformationInjectPosLength(driver);
			case ROUTESENSOR:
				return new TinkerGraphTransformationInjectRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new TinkerGraphTransformationInjectSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new TinkerGraphTransformationInjectSwitchSensor(driver);
			case SWITCHSET:
				return new TinkerGraphTransformationInjectSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
