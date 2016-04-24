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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches;

import java.util.Arrays;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class TinkerGraphMatch {

	protected Map<String, Object> match;

	public TinkerGraphMatch(final Map<String, Object> match) {
		this.match = match;
	}

	public abstract Vertex[] toArray();

	@Override
	public String toString() {
		return "TinkerGraphMatch [match=" + Arrays.toString(toArray()) + "]";
	}
	
	public static TinkerGraphMatch createMatch(final RailwayQuery query, final Map<String, Object> row) {
		switch (query) {
		case CONNECTEDSEGMENTS:
//			return new TinkerGraphConnectedSegmentsMatch(row);
		case POSLENGTH:
			return new TinkerGraphPosLengthMatch(row);
		case ROUTESENSOR:
//			return new TinkerGraphRouteSensorMatch(row);
		case SEMAPHORENEIGHBOR:
//			return new TinkerGraphSemaphoreNeighborMatch(row);
		case SWITCHSENSOR:
//			return new TinkerGraphSwitchSensorMatch(row);
		case SWITCHSET:
//			return new TinkerGraphSwitchSetMatch(row);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}
	
}
