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
package hu.bme.mit.trainbenchmark.benchmark.sql.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Due to the iterator-like nature of the java.sql.ResultSet interface, we cannot store the ResultSet and have to copy its contents. This is
 * implemented for each match in the subclasses of the SQLMatch class.
 * 
 * @author szarnyasg
 * 
 */
public abstract class SqlMatch extends LongMatch {

	public static SqlMatch createMatch(final RailwayQuery query, final ResultSet rs) throws SQLException {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new SqlConnectedSegmentsMatch(rs);
		case POSLENGTH:
			return new SqlPosLengthMatch(rs);
		case ROUTESENSOR:
			return new SqlRouteSensorMatch(rs);
		case SEMAPHORENEIGHBOR:
			return new SqlSemaphoreNeighborMatch(rs);
		case SWITCHSENSOR:
			return new SqlSwitchSensorMatch(rs);
		case SWITCHSET:
			return new SqlSwitchSetMatch(rs);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

}
