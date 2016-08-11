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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.match;

import com.google.common.base.Joiner;

import hu.bme.mit.trainbenchmark.benchmark.matches.Match;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import scala.collection.immutable.Vector;

public abstract class IqdCoreMatch implements Match {

	protected Vector<Object> qs;

	public IqdCoreMatch(final Vector<Object> qs) {
		this.qs = qs;
	}

	public static IqdCoreMatch createMatch(final RailwayQuery query, final Vector<Object> qs) {
		switch (query) {
		case ACTIVEROUTE:
			return new IqdCoreActiveRouteMatch(qs);
		case CONNECTEDSEGMENTS:
			return new IqdCoreConnectedSegmentsMatch(qs);
		case CONNECTEDSEGMENTS_INJECT:
			return new IqdCoreConnectedSegmentsInjectMatch(qs);
		case POSLENGTH:
			return new IqdCorePosLengthMatch(qs);
		case POSLENGTH_INJECT:
			return new IqdCorePosLengthInjectMatch(qs);
		case ROUTESENSOR:
			return new IqdCoreRouteSensorMatch(qs);
		case ROUTESENSOR_INJECT:
			return new IqdCoreRouteSensorInjectMatch(qs);
		case SEMAPHORENEIGHBOR:
			return new IqdCoreSemaphoreNeighborMatch(qs);
		case SEMAPHORENEIGHBOR_INJECT:
			return new IqdCoreSemaphoreNeighborInjectMatch(qs);
		case SWITCHMONITORED:
			return new IqdCoreSwitchMonitoredMatch(qs);
		case SWITCHMONITORED_INJECT:
			return new IqdCoreSwitchMonitoredInjectMatch(qs);
		case SWITCHSET:
			return new IqdCoreSwitchSetMatch(qs);
		case SWITCHSET_INJECT:
			return new IqdCoreSwitchSetInjectMatch(qs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

	@Override
	public String toString() {
		Joiner joiner = Joiner.on(", ");
		return "<" + joiner.join(toArray()) + ">";
	}

}
