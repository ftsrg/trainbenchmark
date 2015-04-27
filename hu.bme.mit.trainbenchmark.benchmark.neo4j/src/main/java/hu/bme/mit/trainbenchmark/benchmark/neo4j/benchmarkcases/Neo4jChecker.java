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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class Neo4jChecker<M extends Neo4jMatch> extends Checker<M> {

	protected final Neo4jDriver neoDriver;

	public Neo4jChecker(final Neo4jDriver neoDriver) {
		super();
		this.neoDriver = neoDriver;
	}

	public static Neo4jChecker newInstance(final Neo4jDriver neoDriver, final Query query) {
		switch (query) {
		case POSLENGTH:
			return new Neo4jPosLengthChecker(neoDriver);
		case ROUTESENSOR:
			return new Neo4jRouteSensorChecker(neoDriver);
		case SEMAPHORENEIGHBOR:
			return new Neo4jSemaphoreNeighborChecker(neoDriver);
		case SWITCHSENSOR:
			return new Neo4jSwitchSensorChecker(neoDriver);
		case SWITCHSET:
			return new Neo4jSwitchSetChecker(neoDriver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
