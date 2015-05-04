/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.fourstore.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FourStoreSwitchSetMatch extends FourStoreMatch {

	public FourStoreSwitchSetMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SEMAPHORE), rs.getLong(VAR_ROUTE), rs.getLong(VAR_SWP), rs.getLong(VAR_SW) };
	}

	public Long getSemaphore() {
		return match[0];
	}

	public Long getRoute() {
		return match[1];
	}

	public Long getSwitchPosition() {
		return match[2];
	}

	public Long getSw() {
		return match[3];
	}

}
