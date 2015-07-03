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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationInjectSwitchSet extends SQLTransformationInject {

	public SQLTransformationInjectSwitchSet(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> switches) throws SQLException {
		for (final Long sw : switches) {
			final String update = "" + //
					"UPDATE " + SWITCH + " " + //
					"SET " + CURRENTPOSITION + " = MOD(" + CURRENTPOSITION + " + 1, 4) " + //
					"WHERE " + ID + " = " + sw + ";";
			sqlDriver.getConnection().createStatement().executeUpdate(update);
		}
	}
}
