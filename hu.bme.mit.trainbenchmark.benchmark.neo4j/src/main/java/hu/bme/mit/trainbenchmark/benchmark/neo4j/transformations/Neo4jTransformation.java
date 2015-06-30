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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class Neo4jTransformation<M> extends Transformation<M> {

	protected Neo4jDriver neoDriver;

	protected Neo4jTransformation(final Neo4jDriver neoDriver) {
		this.neoDriver = neoDriver;
	}

	public static Transformation<?> newInstance(final Neo4jDriver neoDriver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new Neo4jTransformationRepairConnectedSegments(neoDriver);				
			case POSLENGTH:
				return new Neo4jTransformationRepairPosLength(neoDriver);
			case ROUTESENSOR:
				return new Neo4jTransformationRepairRouteSensor(neoDriver);
			case SEMAPHORENEIGHBOR:
				return new Neo4jTransformationRepairSemaphoreNeighbor(neoDriver);
			case SWITCHSENSOR:
				return new Neo4jTransformationRepairSwitchSensor(neoDriver);
			case SWITCHSET:
				return new Neo4jTransformationRepairSwitchSet(neoDriver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new Neo4jTransformationInjectConnectedSegments(neoDriver);				
			case POSLENGTH:
				return new Neo4jTransformationInjectPosLength(neoDriver);
			case ROUTESENSOR:
				return new Neo4jTransformationInjectRouteSensor(neoDriver);
			case SEMAPHORENEIGHBOR:
				return new Neo4jTransformationInjectSemaphoreNeighbor(neoDriver);
			case SWITCHSENSOR:
				return new Neo4jTransformationInjectSwitchSensor(neoDriver);
			case SWITCHSET:
				return new Neo4jTransformationInjectSwitchSet(neoDriver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
