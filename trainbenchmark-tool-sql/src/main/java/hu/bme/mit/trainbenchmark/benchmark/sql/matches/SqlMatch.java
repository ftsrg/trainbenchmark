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
package hu.bme.mit.trainbenchmark.benchmark.sql.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Due to the iterator-like nature of the java.sql.ResultSet interface, we cannot store the ResultSet and have to copy its contents. This is
 * implemented for each match in the subclasses of the SqlMatch class.
 *
 */
public abstract class SqlMatch extends LongMatch {

	public static SqlMatch createMatch(final RailwayQuery query, final ResultSet rs) throws SQLException {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new SqlConnectedSegmentsMatch(rs);
		case CONNECTEDSEGMENTS_INJECT:
			return new SqlConnectedSegmentsInjectMatch(rs);
		case POSLENGTH:
			return new SqlPosLengthMatch(rs);
		case POSLENGTH_INJECT:
			return new SqlPosLengthInjectMatch(rs);
		case ROUTESENSOR:
			return new SqlRouteSensorMatch(rs);
		case ROUTESENSOR_INJECT:
			return new SqlRouteSensorInjectMatch(rs);
		case SEMAPHORENEIGHBOR:
			return new SqlSemaphoreNeighborMatch(rs);
		case SEMAPHORENEIGHBOR_INJECT:
			return new SqlSemaphoreNeighborInjectMatch(rs);
		case SWITCHMONITORED:
			return new SqlSwitchMonitoredMatch(rs);
		case SWITCHMONITORED_INJECT:
			return new SqlSwitchMonitoredInjectMatch(rs);
		case SWITCHSET:
			return new SqlSwitchSetMatch(rs);
		case SWITCHSET_INJECT:
			return new SqlSwitchSetInjectMatch(rs);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

}
