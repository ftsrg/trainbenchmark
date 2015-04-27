/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user.Neo4jTransformationUserPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user.Neo4jTransformationUserRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user.Neo4jTransformationUserSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user.Neo4jTransformationUserSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user.Neo4jTransformationUserSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class Neo4jTransformation<M> extends Transformation<M> {

	// query = FileUtils.readFileToString(new File(bc.getWorkspacePath()
	// + "/hu.bme.mit.trainbenchmark.benchmark.neo4j/src/main/resources/queries/" + getName() + ".cypher"));

	public static Transformation<?> newInstance(final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case POSLENGTH:
				return new Neo4jTransformationRepairPosLength();
			case ROUTESENSOR:
				return new Neo4jTransformationRepairRouteSensor();
			case SEMAPHORENEIGHBOR:
				return new Neo4jTransformationRepairSemaphoreNeighbor();
			case SWITCHSENSOR:
				return new Neo4jTransformationRepairSwitchSensor();
			case SWITCHSET:
				return new Neo4jTransformationRepairSwitchSet();
			default:
				break;
			}
		case USER:
			switch (query) {
			case POSLENGTH:
				return new Neo4jTransformationUserPosLength();
			case ROUTESENSOR:
				return new Neo4jTransformationUserRouteSensor();
			case SEMAPHORENEIGHBOR:
				return new Neo4jTransformationUserSemaphoreNeighbor();
			case SWITCHSENSOR:
				return new Neo4jTransformationUserSwitchSensor();
			case SWITCHSET:
				return new Neo4jTransformationUserSwitchSet();
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
}
