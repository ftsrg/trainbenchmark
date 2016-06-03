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

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core.TinkerGraphConnectedSegmentsChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core.TinkerGraphPosLengthChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core.TinkerGraphRouteSensorChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core.TinkerGraphSemaphoreNeighborChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core.TinkerGraphSwitchMonitoredChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core.TinkerGraphSwitchSetChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class TinkerGraphModelQuery<TMatch extends TinkerGraphMatch> extends ModelQuery<TMatch, TinkerGraphDriver> {

	public TinkerGraphModelQuery(final TinkerGraphDriver driver) {
		super(driver);
	}

	public static TinkerGraphModelQuery newInstance(final TinkerGraphDriver driver, final RailwayQuery query) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new TinkerGraphConnectedSegmentsChecker(driver);
		case POSLENGTH:
			return new TinkerGraphPosLengthChecker(driver);
		case ROUTESENSOR:
			return new TinkerGraphRouteSensorChecker(driver);
		case SEMAPHORENEIGHBOR:
			return new TinkerGraphSemaphoreNeighborChecker(driver);
		case SWITCHMONITORED:
			return new TinkerGraphSwitchMonitoredChecker(driver);
		case SWITCHSET:
			return new TinkerGraphSwitchSetChecker(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
