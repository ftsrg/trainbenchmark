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

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class Neo4jCoreModelQuery<TMatch extends Neo4jMatch> extends ModelQuery<TMatch, Neo4jDriver> {

	public Neo4jCoreModelQuery(final Neo4jDriver driver) {
		super(driver);
	}

	public static Neo4jCoreModelQuery<?> newInstance(final Neo4jDriver driver, final RailwayQuery query) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new Neo4jCoreConnectedSegmentsChecker(driver);
		case POSLENGTH:
			return new Neo4jCorePosLengthChecker(driver);
		case ROUTESENSOR:
			return new Neo4jCoreRouteSensorChecker(driver);
		case SEMAPHORENEIGHBOR:
			return new Neo4jCoreSemaphoreNeighborChecker(driver);
		case SWITCHMONITORED:
			return new Neo4jCoreSwitchMonitoredChecker(driver);
		case SWITCHSET:
			return new Neo4jCoreSwitchSetChecker(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
