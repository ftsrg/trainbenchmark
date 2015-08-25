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

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SW;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSensorMatch;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSwitchSensorMatch extends SQLMatch implements SwitchSensorMatch {

	public SQLSwitchSensorMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SW) };
	}

	@Override
	public Long getSw() {
		return match[0];
	}

}
