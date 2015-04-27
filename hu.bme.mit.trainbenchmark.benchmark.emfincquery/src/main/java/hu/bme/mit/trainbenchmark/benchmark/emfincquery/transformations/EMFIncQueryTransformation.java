/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformation;

public abstract class EMFIncQueryTransformation<M> extends Transformation<M> {

	public static Transformation<?> newInstance(final EMFDriver driver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case POSLENGTH:
				return new EMFIncQueryTransformationRepairPosLength();
			case ROUTESENSOR:
				return new EMFIncQueryTransformationRepairRouteSensor();
			case SEMAPHORENEIGHBOR:
				return new EMFIncQueryTransformationRepairSemaphoreNeighbor();
			case SWITCHSENSOR:
				return new EMFIncQueryTransformationRepairSwitchSensor();
			case SWITCHSET:
				return new EMFIncQueryTransformationRepairSwitchSet();
			default:
				break;
			}
		case USER:
			return EMFUserTransformation.newInstance(driver, query);
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
