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

import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Scenario;



public abstract class IqdCoreTransformation<TObject> extends ModelTransformation<TObject, IqdCoreDriver> {

	protected IqdCoreTransformation(final IqdCoreDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public static ModelTransformation<?, IqdCoreDriver> newInstance(final IqdCoreDriver driver, final RailwayQuery query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new IqdCoreTransformationRepairConnectedSegments(driver);
			case POSLENGTH:
				return new IqdCoreTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new IqdCoreTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new IqdCoreTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHMONITORED:
				return new IqdCoreTransformationRepairSwitchMonitored(driver);
			case SWITCHSET:
				return new IqdCoreTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
