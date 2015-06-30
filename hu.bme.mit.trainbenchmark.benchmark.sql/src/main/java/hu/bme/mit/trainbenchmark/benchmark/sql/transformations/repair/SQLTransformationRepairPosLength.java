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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ID;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLPosLengthMatch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationRepairPosLength extends SQLTransformationRepair<SQLPosLengthMatch> {

	public SQLTransformationRepairPosLength(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<SQLPosLengthMatch> matches) throws IOException {
		for (final SQLPosLengthMatch match : matches) {
			try {
				final String update = String.format("" + //
						"UPDATE %s " + //
						"SET %s = -%s + 1 " + //
						"WHERE `%s` = %d;", //
						SEGMENT, LENGTH, LENGTH, ID, match.getSegment());
				sqlDriver.getConnection().createStatement().executeUpdate(update);
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}
}
