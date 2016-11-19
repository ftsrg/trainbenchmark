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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import java.sql.ResultSet;
import java.sql.SQLException;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredMatch;

public class SqlSwitchMonitoredMatch extends SqlMatch implements SwitchMonitoredMatch {

	public SqlSwitchMonitoredMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SW) };
	}

	@Override
	public Long getSw() {
		return match[0];
	}

}
