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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.match;

import com.google.common.base.Joiner;

import hu.bme.mit.trainbenchmark.benchmark.matches.Match;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import scala.collection.JavaConversions;
import scala.collection.immutable.Map;

public abstract class IngraphMatch implements Match {

	protected java.util.Map<Object, Object> qs;

	public IngraphMatch(final scala.collection.immutable.Map<Object, Object> qs) {
		// TODO has a negative effect on performance
		this.qs = JavaConversions.mapAsJavaMap(qs);
	}

	public static IngraphMatch createMatch(final RailwayQuery query, final Map<Object, Object> qs) {
		switch (query) {
		case ACTIVEROUTE:
			return new IngraphActiveRouteMatch(qs);
		case CONNECTEDSEGMENTS:
			return new IngraphConnectedSegmentsMatch(qs);
		case CONNECTEDSEGMENTS_INJECT:
			return new IngraphConnectedSegmentsInjectMatch(qs);
		case POSLENGTH:
			return new IngraphPosLengthMatch(qs);
		case POSLENGTH_INJECT:
			return new IngraphPosLengthInjectMatch(qs);
		case ROUTESENSOR:
			return new IngraphRouteSensorMatch(qs);
		case ROUTESENSOR_INJECT:
			return new IngraphRouteSensorInjectMatch(qs);
		case SEMAPHORENEIGHBOR:
			return new IngraphSemaphoreNeighborMatch(qs);
		case SEMAPHORENEIGHBOR_INJECT:
			return new IngraphSemaphoreNeighborInjectMatch(qs);
		case SWITCHMONITORED:
			return new IngraphSwitchMonitoredMatch(qs);
		case SWITCHMONITORED_INJECT:
			return new IngraphSwitchMonitoredInjectMatch(qs);
		case SWITCHSET:
			return new IngraphSwitchSetMatch(qs);
		case SWITCHSET_INJECT:
			return new IngraphSwitchSetInjectMatch(qs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

	@Override
	public String toString() {
		final Joiner joiner = Joiner.on(", ");
		return "<" + joiner.join(toArray()) + ">";
	}

}
