/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.mysql.benchmarkcases;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class RouteRouteDefinition extends MySQLBenchmarkCase {

	@Override
	public String getName() {
		return "RouteRouteDefinition";
	}

	final Multimap<Long, Long> connectedNodes = HashMultimap.create();

	@Override
	public void check() throws IOException {
		bmr.startStopper();

		try (ResultSet rs = con.createStatement().executeQuery("SELECT * FROM trainbenchmark.Route_routeDefinition;")) {

			while (rs.next()) {
				connectedNodes.put((long) rs.getInt(1), (long) rs.getInt(2));
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		bmr.addInvalid(connectedNodes.size());
		bmr.addCheckTime();
	}

	@Override
	public int getResultSize() {
		return connectedNodes.size();
	}

}
