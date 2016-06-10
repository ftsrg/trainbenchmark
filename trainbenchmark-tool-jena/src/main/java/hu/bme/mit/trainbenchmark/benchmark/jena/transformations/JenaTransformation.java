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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class JenaTransformation<TObject> extends ModelTransformation<TObject, JenaDriver> {

	protected JenaTransformation(final JenaDriver driver) {
		super(driver);
	}

	public static ModelTransformation<?, JenaDriver> newInstance(final JenaDriver driver, final RailwayQuery query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new JenaTransformationRepairConnectedSegments(driver);
			case POSLENGTH:
				return new JenaTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new JenaTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new JenaTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHMONITORED:
				return new JenaTransformationRepairSwitchMonitored(driver);
			case SWITCHSET:
				return new JenaTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new JenaTransformationInjectConnectedSegments(driver);
			case POSLENGTH:
				return new JenaTransformationInjectPosLength(driver);
			case ROUTESENSOR:
				return new JenaTransformationInjectRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new JenaTransformationInjectSemaphoreNeighbor(driver);
			case SWITCHMONITORED:
				return new JenaTransformationInjectSwitchMonitored(driver);
			case SWITCHSET:
				return new JenaTransformationInjectSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
