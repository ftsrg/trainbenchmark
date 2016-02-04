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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import java.util.Arrays;
import java.util.Map;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class Neo4jMatch {

	protected Map<String, Object> match;

	public Neo4jMatch(final Map<String, Object> match) {
		this.match = match;
	}

	public abstract Node[] toArray();

	@Override
	public String toString() {
		return "Neo4jMatch [match=" + Arrays.toString(toArray()) + "]";
	}
	
	public static Neo4jMatch createMatch(final RailwayQuery query, final Map<String, Object> row) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new Neo4jConnectedSegmentsMatch(row);
		case POSLENGTH:
			return new Neo4jPosLengthMatch(row);
		case ROUTESENSOR:
			return new Neo4jRouteSensorMatch(row);
		case SEMAPHORENEIGHBOR:
			return new Neo4jSemaphoreNeighborMatch(row);
		case SWITCHSENSOR:
			return new Neo4jSwitchSensorMatch(row);
		case SWITCHSET:
			return new Neo4jSwitchSetMatch(row);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}
	
}
