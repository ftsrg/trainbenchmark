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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;

import java.sql.ResultSet;
import java.sql.SQLException;

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborInjectMatch;

public class SqlSemaphoreNeighborInjectMatch extends SqlMatch implements SemaphoreNeighborInjectMatch {

	public SqlSemaphoreNeighborInjectMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_ROUTE) };
	}

	@Override
	public Long getRoute() {
		return match[0];
	}

}
