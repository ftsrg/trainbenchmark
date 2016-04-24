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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.core;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class Neo4jCoreChecker<TMatch extends Neo4jMatch> extends Checker<TMatch> {

	protected final Neo4jDriver driver;

	public Neo4jCoreChecker(final Neo4jDriver driver) {
		super();
		this.driver = driver;
	}

	public static Neo4jCoreChecker newInstance(final Neo4jDriver driver, final RailwayQuery query) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new Neo4jCoreConnectedSegmentsChecker(driver);
		case POSLENGTH:
			return new Neo4jCorePosLengthChecker(driver);
		case ROUTESENSOR:
			return new Neo4jCoreRouteSensorChecker(driver);
		case SEMAPHORENEIGHBOR:
			return new Neo4jCoreSemaphoreNeighborChecker(driver);
		case SWITCHSENSOR:
			return new Neo4jCoreSwitchSensorChecker(driver);
		case SWITCHSET:
			return new Neo4jCoreSwitchSetChecker(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
