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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class Rdf4jTransformation<TRdf4jMatch extends Rdf4jMatch, TRdf4jDriver extends Rdf4jDriver>
		extends ModelTransformation<TRdf4jMatch, TRdf4jDriver> {

	protected Rdf4jTransformation(final TRdf4jDriver driver) {
		super(driver);
	}

	public static <TRdf4jDriver extends Rdf4jDriver> ModelTransformation<?, ?> newInstance(final TRdf4jDriver driver, final RailwayQuery query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new Rdf4jTransformationRepairConnectedSegments<TRdf4jDriver>(driver);
			case POSLENGTH:
				return new Rdf4jTransformationRepairPosLength<TRdf4jDriver>(driver);
			case ROUTESENSOR:
				return new Rdf4jTransformationRepairRouteSensor<TRdf4jDriver>(driver);
			case SEMAPHORENEIGHBOR:
				return new Rdf4jTransformationRepairSemaphoreNeighbor<TRdf4jDriver>(driver);
			case SWITCHMONITORED:
				return new Rdf4jTransformationRepairSwitchMonitored<TRdf4jDriver>(driver);
			case SWITCHSET:
				return new Rdf4jTransformationRepairSwitchSet<TRdf4jDriver>(driver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new Rdf4jTransformationInjectConnectedSegments<TRdf4jDriver>(driver);
			case POSLENGTH:
				return new Rdf4jTransformationInjectPosLength<TRdf4jDriver>(driver);
			case ROUTESENSOR:
				return new Rdf4jTransformationInjectRouteSensor<TRdf4jDriver>(driver);
			case SEMAPHORENEIGHBOR:
				return new Rdf4jTransformationInjectSemaphoreNeighbor<TRdf4jDriver>(driver);
			case SWITCHMONITORED:
				return new Rdf4jTransformationInjectSwitchMonitored<TRdf4jDriver>(driver);
			case SWITCHSET:
				return new Rdf4jTransformationInjectSwitchSet<TRdf4jDriver>(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
