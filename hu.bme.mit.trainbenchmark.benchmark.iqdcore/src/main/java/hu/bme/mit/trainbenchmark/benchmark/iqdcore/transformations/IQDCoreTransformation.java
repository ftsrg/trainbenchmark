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

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.*;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public abstract class IQDCoreTransformation<TObject> extends ModelTransformation<TObject, IQDCoreDriver> {

	protected IQDCoreTransformation(final IQDCoreDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public static ModelTransformation<?, IQDCoreDriver> newInstance(final IQDCoreDriver driver, final RailwayQuery query, final ScenarioEnum scenario) {
		switch (scenario) {
		case BATCH:
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new IQDCoreTransformationRepairConnectedSegments(driver);
			case POSLENGTH:
				return new IQDCoreTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new IQDCoreTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new IQDCoreTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new IQDCoreTransformationRepairSwitchSensor(driver);
			case SWITCHSET:
				return new IQDCoreTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
