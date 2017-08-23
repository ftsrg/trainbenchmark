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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.matches;

import java.util.Arrays;
import java.util.Map;

import hu.bme.mit.trainbenchmark.benchmark.matches.BaseMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class GraphflowMatch extends BaseMatch {

	protected Map<String, Object> match;

	public GraphflowMatch(final Map<String, Object> match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "GraphflowMatch [match=" + Arrays.toString(toArray()) + "]";
	}

	public static GraphflowMatch createMatch(final RailwayQuery query, final Map<String, Object> match) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new GraphflowConnectedSegmentsMatch(match);
		case CONNECTEDSEGMENTS_INJECT:
			return new GraphflowConnectedSegmentsInjectMatch(match);
		case POSLENGTH:
			return new GraphflowPosLengthMatch(match);
		case POSLENGTH_INJECT:
			return new GraphflowPosLengthInjectMatch(match);
		case ROUTESENSOR:
			return new GraphflowRouteSensorMatch(match);
		case ROUTESENSOR_INJECT:
			return new GraphflowRouteSensorInjectMatch(match);
		case SEMAPHORENEIGHBOR:
			return new GraphflowSemaphoreNeighborMatch(match);
		case SEMAPHORENEIGHBOR_INJECT:
			return new GraphflowSemaphoreNeighborInjectMatch(match);
		case SWITCHMONITORED:
			return new GraphflowSwitchMonitoredMatch(match);
		case SWITCHMONITORED_INJECT:
			return new GraphflowSwitchMonitoredInjectMatch(match);
		case SWITCHSET:
			return new GraphflowSwitchSetMatch(match);
		case SWITCHSET_INJECT:
			return new GraphflowSwitchSetInjectMatch(match);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

}
