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

import static hu.bme.mit.trainbenchmark.constants.ScenarioEnum.INJECT;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryBaseDriver;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.transformations.repair.EMFIncQueryTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public abstract class EMFIncQueryTransformation<TMatch extends BasePatternMatch>
	extends ModelTransformation<TMatch, EMFIncQueryBaseDriver<? extends BasePatternMatch>> {

	public EMFIncQueryTransformation(final EMFIncQueryBaseDriver<? extends BasePatternMatch> driver) {
		super(driver);
	}

	public static ModelTransformation<?, ?> newInstance(final EMFIncQueryBaseDriver<? extends BasePatternMatch> driver,
			final RailwayQuery query, final ScenarioEnum scenario) {
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
			return EmfTransformation.newInstance(driver, query, INJECT);
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
