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
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class TinkerGraphQuery<TMatch extends TinkerGraphMatch> extends ModelQuery<TMatch, TinkerGraphDriver> {

	public TinkerGraphQuery(final TinkerGraphDriver driver) {
		super(driver);
	}

	public static TinkerGraphQuery newInstance(final TinkerGraphDriver driver, final RailwayQuery query) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new TinkerGraphQueryConnectedSegments(driver);
		case POSLENGTH:
			return new TinkerGraphQueryPosLength(driver);
		case ROUTESENSOR:
			return new TinkerGraphQueryRouteSensor(driver);
		case SEMAPHORENEIGHBOR:
			return new TinkerGraphQuerySemaphoreNeighbor(driver);
		case SWITCHMONITORED:
			return new TinkerGraphQuerySwitchMonitored(driver);
		case SWITCHSET:
			return new TinkerGraphQuerySwitchSet(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
