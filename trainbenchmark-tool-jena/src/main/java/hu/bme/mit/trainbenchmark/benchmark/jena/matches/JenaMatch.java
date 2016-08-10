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
package hu.bme.mit.trainbenchmark.benchmark.jena.matches;

import org.apache.jena.ext.com.google.common.base.Joiner;
import org.apache.jena.query.QuerySolution;

import hu.bme.mit.trainbenchmark.benchmark.matches.Match;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class JenaMatch implements Match {

	protected QuerySolution qs;

	public JenaMatch(final QuerySolution qs) {
		this.qs = qs;
	}

	public static JenaMatch createMatch(final RailwayQuery query, final QuerySolution qs) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new JenaConnectedSegmentsMatch(qs);
		case CONNECTEDSEGMENTS_INJECT:
			return new JenaConnectedSegmentsInjectMatch(qs);
		case POSLENGTH:
			return new JenaPosLengthMatch(qs);
		case POSLENGTH_INJECT:
			return new JenaPosLengthInjectMatch(qs);
		case ROUTESENSOR:
			return new JenaRouteSensorMatch(qs);
		case ROUTESENSOR_INJECT:
			return new JenaRouteSensorInjectMatch(qs);
		case SEMAPHORENEIGHBOR:
			return new JenaSemaphoreNeighborMatch(qs);
		case SEMAPHORENEIGHBOR_INJECT:
			return new JenaSemaphoreNeighborInjectMatch(qs);
		case SWITCHMONITORED:
			return new JenaSwitchMonitoredMatch(qs);
		case SWITCHMONITORED_INJECT:
			return new JenaSwitchMonitoredInjectMatch(qs);
		case SWITCHSET:
			return new JenaSwitchSetMatch(qs);
		case SWITCHSET_INJECT:
			return new JenaSwitchSetInjectMatch(qs);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

	@Override
	public String toString() {
		final Joiner joiner = Joiner.on(", ");
		return joiner.join(toArray());
	}
	
}
