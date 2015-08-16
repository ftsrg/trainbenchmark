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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;

public abstract class JenaTransformation<M> extends Transformation<M> {

	protected JenaDriver jenaDriver;

	protected JenaTransformation(final JenaDriver jenaDriver) {
		this.jenaDriver = jenaDriver;
	}

	public static Transformation<?> newInstance(final JenaDriver jenaDriver, final Query query, final ScenarioConstants scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new JenaTransformationRepairConnectedSegments(jenaDriver);
			case POSLENGTH:
				return new JenaTransformationRepairPosLength(jenaDriver);
			case ROUTESENSOR:
				return new JenaTransformationRepairRouteSensor(jenaDriver);
			case SEMAPHORENEIGHBOR:
				return new JenaTransformationRepairSemaphoreNeighbor(jenaDriver);
			case SWITCHSENSOR:
				return new JenaTransformationRepairSwitchSensor(jenaDriver);
			case SWITCHSET:
				return new JenaTransformationRepairSwitchSet(jenaDriver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new JenaTransformationInjectConnectedSegments(jenaDriver);
			case POSLENGTH:
				return new JenaTransformationInjectPosLength(jenaDriver);
			case ROUTESENSOR:
				return new JenaTransformationInjectRouteSensor(jenaDriver);
			case SEMAPHORENEIGHBOR:
				return new JenaTransformationInjectSemaphoreNeighbor(jenaDriver);
			case SWITCHSENSOR:
				return new JenaTransformationInjectSwitchSensor(jenaDriver);
			case SWITCHSET:
				return new JenaTransformationInjectSwitchSet(jenaDriver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
