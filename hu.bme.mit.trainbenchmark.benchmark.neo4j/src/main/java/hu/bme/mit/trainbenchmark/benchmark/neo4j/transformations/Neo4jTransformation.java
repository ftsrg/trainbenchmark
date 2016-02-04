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
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public abstract class Neo4jTransformation<TObject> extends Transformation<TObject, Neo4jDriver> {

	protected Neo4jTransformation(final Neo4jDriver driver) {
		super(driver);
	}

	public static Transformation<?, ?> newInstance(final Neo4jDriver driver, final RailwayQuery query, final ScenarioEnum scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new Neo4jTransformationRepairConnectedSegments(driver);				
			case POSLENGTH:
				return new Neo4jTransformationRepairPosLength(driver);
			case ROUTESENSOR:
				return new Neo4jTransformationRepairRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new Neo4jTransformationRepairSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new Neo4jTransformationRepairSwitchSensor(driver);
			case SWITCHSET:
				return new Neo4jTransformationRepairSwitchSet(driver);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new Neo4jTransformationInjectConnectedSegments(driver);				
			case POSLENGTH:
				return new Neo4jTransformationInjectPosLength(driver);
			case ROUTESENSOR:
				return new Neo4jTransformationInjectRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new Neo4jTransformationInjectSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new Neo4jTransformationInjectSwitchSensor(driver);
			case SWITCHSET:
				return new Neo4jTransformationInjectSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
