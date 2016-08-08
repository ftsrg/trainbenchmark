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
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SesameMatch {

	protected BindingSet bs;

	public SesameMatch(final BindingSet bs) {
		this.bs = bs;
	}

	public abstract URI[] toArray();

	public static SesameMatch createMatch(final RailwayQuery query, final BindingSet bs) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new SesameConnectedSegmentsMatch(bs);
		case CONNECTEDSEGMENTS_INJECT:
			return new SesameConnectedSegmentsInjectMatch(bs);
		case POSLENGTH:
			return new SesamePosLengthMatch(bs);
		case POSLENGTH_INJECT:
			return new SesamePosLengthInjectMatch(bs);
		case ROUTESENSOR:
			return new SesameRouteSensorMatch(bs);
		case ROUTESENSOR_INJECT:
			return new SesameRouteSensorInjectMatch(bs);
		case SEMAPHORENEIGHBOR:
			return new SesameSemaphoreNeighborMatch(bs);
		case SEMAPHORENEIGHBOR_INJECT:
			return new SesameSemaphoreNeighborInjectMatch(bs);
		case SWITCHMONITORED:
			return new SesameSwitchMonitoredMatch(bs);
		case SWITCHMONITORED_INJECT:
			return new SesameSwitchMonitoredInjectMatch(bs);
		case SWITCHSET:
			return new SesameSwitchSetMatch(bs);
		case SWITCHSET_INJECT:
			return new SesameSwitchSetInjectMatch(bs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

}
