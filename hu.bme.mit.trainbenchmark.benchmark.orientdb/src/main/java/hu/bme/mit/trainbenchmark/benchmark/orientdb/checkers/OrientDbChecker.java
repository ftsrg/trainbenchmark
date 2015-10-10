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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class OrientDbChecker<TMatch extends OrientDbMatch> extends Checker<TMatch> {

	protected final OrientDbDriver driver;
	protected String queryPath;

	public OrientDbChecker(final OrientDbDriver driver) {
		super();
		this.driver = driver;
		this.queryPath = driver.getBenchmarkDir() + "/src/main/resources/queries/";
	}

	public static OrientDbChecker newInstance(final OrientDbDriver driver, final Query query) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new OrientDbConnectedSegmentsChecker(driver);
		case POSLENGTH:
			return new OrientDbPosLengthChecker(driver);
		case ROUTESENSOR:
			return new OrientDbRouteSensorChecker(driver);
		case SEMAPHORENEIGHBOR:
			return new OrientDbSemaphoreNeighborChecker(driver);
		case SWITCHSENSOR:
			return new OrientDbSwitchSensorChecker(driver);
		case SWITCHSET:
			return new OrientDbSwitchSetChecker(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}
	
	public String getQueryPath() {
		return queryPath;
	}
	
}
