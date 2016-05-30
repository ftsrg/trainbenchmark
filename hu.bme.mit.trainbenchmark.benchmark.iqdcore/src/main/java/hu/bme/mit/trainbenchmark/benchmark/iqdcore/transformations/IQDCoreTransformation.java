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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations;

import hu.bme.mit.incquerydcore.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreReader;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IQDCoreTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IQDCoreTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IQDCoreTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IQDCoreTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IQDCoreTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IQDCoreTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class IQDCoreTransformation<TObject> extends Transformation<TObject, WildcardInput> {

	protected WildcardInput input;

	protected IQDCoreTransformation(final WildcardInput input) {
		super(input);
		this.input = input;
	}

	public static Transformation<?, WildcardInput> newInstance(final WildcardInput input, final Query query, final Scenario scenario) {
		switch (scenario) {
		case BATCH:
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new IQDCoreTransformationRepairConnectedSegments(input);
			case POSLENGTH:
				return new IQDCoreTransformationRepairPosLength(input);
			case ROUTESENSOR:
				return new IQDCoreTransformationRepairRouteSensor(input);
			case SEMAPHORENEIGHBOR:
				return new IQDCoreTransformationRepairSemaphoreNeighbor(input);
			case SWITCHSENSOR:
				return new IQDCoreTransformationRepairSwitchSensor(input);
			case SWITCHSET:
				return new IQDCoreTransformationRepairSwitchSet(input);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
