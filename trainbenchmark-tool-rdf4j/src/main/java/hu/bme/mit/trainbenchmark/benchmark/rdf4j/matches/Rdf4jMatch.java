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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches;

import org.eclipse.rdf4j.query.BindingSet;

import hu.bme.mit.trainbenchmark.benchmark.matches.Match;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class Rdf4jMatch implements Match {

	protected BindingSet bs;

	public Rdf4jMatch(final BindingSet bs) {
		this.bs = bs;
	}

	public static Rdf4jMatch createMatch(final RailwayQuery query, final BindingSet bs) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new Rdf4jConnectedSegmentsMatch(bs);
		case CONNECTEDSEGMENTS_INJECT:
			return new Rdf4jConnectedSegmentsInjectMatch(bs);
		case POSLENGTH:
			return new Rdf4jPosLengthMatch(bs);
		case POSLENGTH_INJECT:
			return new Rdf4jPosLengthInjectMatch(bs);
		case ROUTESENSOR:
			return new Rdf4jRouteSensorMatch(bs);
		case ROUTESENSOR_INJECT:
			return new Rdf4jRouteSensorInjectMatch(bs);
		case SEMAPHORENEIGHBOR:
			return new Rdf4jSemaphoreNeighborMatch(bs);
		case SEMAPHORENEIGHBOR_INJECT:
			return new Rdf4jSemaphoreNeighborInjectMatch(bs);
		case SWITCHMONITORED:
			return new Rdf4jSwitchMonitoredMatch(bs);
		case SWITCHMONITORED_INJECT:
			return new Rdf4jSwitchMonitoredInjectMatch(bs);
		case SWITCHSET:
			return new Rdf4jSwitchSetMatch(bs);
		case SWITCHSET_INJECT:
			return new Rdf4jSwitchSetInjectMatch(bs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

}
