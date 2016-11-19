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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_POSITION;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;

import java.sql.ResultSet;
import java.sql.SQLException;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;

public class SqlSwitchSetMatch extends SqlMatch implements SwitchSetMatch {

	public SqlSwitchSetMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SEMAPHORE), rs.getLong(VAR_ROUTE), rs.getLong(VAR_SWP), rs.getLong(VAR_SW),
				rs.getLong(VAR_POSITION), rs.getLong(VAR_CURRENTPOSITION) };
	}

	@Override
	public Long getSemaphore() {
		return match[0];
	}

	@Override
	public Long getRoute() {
		return match[1];
	}

	@Override
	public Long getSwP() {
		return match[2];
	}

	@Override
	public Long getSw() {
		return match[3];
	}

	public Long getPosition() {
		return match[4];
	}

	public Long getCurrentPosition() {
		return match[5];
	}

}
