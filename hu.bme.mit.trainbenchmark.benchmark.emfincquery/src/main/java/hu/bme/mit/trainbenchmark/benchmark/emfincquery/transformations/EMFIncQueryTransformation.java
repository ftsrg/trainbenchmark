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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations;

import static hu.bme.mit.trainbenchmark.constants.ScenarioConstants.INJECT;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;

public abstract class EMFIncQueryTransformation<M> extends Transformation<M> {

	protected final EMFIncQueryDriver<?> driver;

	public EMFIncQueryTransformation(final EMFIncQueryDriver<?> driver) {
		super();
		this.driver = driver;
	}

	protected static Transformation<?> newConcreteInstance(Driver driver, final Query query,
			final ScenarioConstants scenario) {
		return newConcreteInstance((EMFIncQueryDriver) driver, query, scenario);
	}

	protected static Transformation<?> newConcreteInstance(final EMFIncQueryDriver<?> driver,
			final Query query, final ScenarioConstants scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new EMFIncQueryTransformationRepairConnectedSegments(driver);
			case POSLENGTH:
				return new EMFIncQueryTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new EMFIncQueryTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new EMFIncQueryTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new EMFIncQueryTransformationRepairSwitchSensor(driver);
			case SWITCHSET:
				return new EMFIncQueryTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		case INJECT:
			return EMFTransformation.newInstance(driver, query, INJECT);
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: "
				+ scenario);
	}
}
