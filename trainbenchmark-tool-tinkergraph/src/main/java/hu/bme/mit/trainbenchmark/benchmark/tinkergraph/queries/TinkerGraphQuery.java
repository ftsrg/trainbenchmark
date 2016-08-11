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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class TinkerGraphQuery<TMatch extends TinkerGraphMatch, TTinkerGraphDriver extends TinkerGraphDriver> extends ModelQuery<TMatch, TTinkerGraphDriver> {

	public TinkerGraphQuery(final RailwayQuery query, final TTinkerGraphDriver driver) {
		super(query, driver);
	}

	public static <TTinkerGraphDriver extends TinkerGraphDriver> TinkerGraphQuery<?, TTinkerGraphDriver> newInstance(final RailwayQuery query, final TTinkerGraphDriver driver) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new TinkerGraphQueryConnectedSegments<>(driver);
		case CONNECTEDSEGMENTS_INJECT:
			return new TinkerGraphQueryConnectedSegmentsInject<>(driver);
		case POSLENGTH:
			return new TinkerGraphQueryPosLength<>(driver);
		case POSLENGTH_INJECT:
			return new TinkerGraphQueryPosLengthInject<>(driver);
		case ROUTESENSOR:
			return new TinkerGraphQueryRouteSensor<>(driver);
		case ROUTESENSOR_INJECT:
			return new TinkerGraphQueryRouteSensorInject<>(driver);
		case SEMAPHORENEIGHBOR:
			return new TinkerGraphQuerySemaphoreNeighbor<>(driver);
		case SEMAPHORENEIGHBOR_INJECT:
			return new TinkerGraphQuerySemaphoreNeighborInject<>(driver);
		case SWITCHMONITORED:
			return new TinkerGraphQuerySwitchMonitored<>(driver);
		case SWITCHMONITORED_INJECT:
			return new TinkerGraphQuerySwitchMonitoredInject<>(driver);
		case SWITCHSET:
			return new TinkerGraphQuerySwitchSet<>(driver);
		case SWITCHSET_INJECT:
			return new TinkerGraphQuerySwitchSetInject<>(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
