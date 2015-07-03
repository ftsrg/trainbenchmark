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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class SQLTransformationInjectPosLength extends SQLTransformationInject {

	public SQLTransformationInjectPosLength(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<Long> segments) throws IOException {
		for (final Long segment : segments) {
			try {
				final String update = "UPDATE "+ SEGMENT + " SET " + LENGTH + " = 0 WHERE " + LENGTH + " = " + segment + ";";
				sqlDriver.getConnection().createStatement().executeUpdate(update);
			} catch (final SQLException e) {
				throw new IOException(e);
			}
		}
	}

}
