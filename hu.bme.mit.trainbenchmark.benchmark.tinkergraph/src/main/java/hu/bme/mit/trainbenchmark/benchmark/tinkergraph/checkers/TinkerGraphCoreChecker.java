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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class TinkerGraphCoreChecker<TMatch extends TinkerGraphMatch> extends Checker<TMatch> {

	protected final TinkerGraphDriver driver;

	public TinkerGraphCoreChecker(final TinkerGraphDriver driver) {
		super();
		this.driver = driver;
	}

	public static TinkerGraphCoreChecker newInstance(final TinkerGraphDriver driver, final RailwayQuery query) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new TinkerGraphCoreConnectedSegmentsChecker(driver);
		case POSLENGTH:
			return new TinkerGraphCorePosLengthChecker(driver);
		case ROUTESENSOR:
			return new TinkerGraphCoreRouteSensorChecker(driver);
		case SEMAPHORENEIGHBOR:
			return new TinkerGraphCoreSemaphoreNeighborChecker(driver);
		case SWITCHSENSOR:
			return new TinkerGraphCoreSwitchSensorChecker(driver);
		case SWITCHSET:
			return new TinkerGraphCoreSwitchSetChecker(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
