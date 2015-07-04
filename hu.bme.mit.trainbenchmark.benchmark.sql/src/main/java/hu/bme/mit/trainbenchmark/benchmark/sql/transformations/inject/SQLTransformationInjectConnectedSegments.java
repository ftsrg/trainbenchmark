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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class SQLTransformationInjectConnectedSegments extends SQLTransformationInject {

	protected final String updateQuery;
	protected PreparedStatement preparedUpdateStatement;

	public SQLTransformationInjectConnectedSegments(final SQLDriver sqlDriver, final BenchmarkConfig bc) throws IOException {
		super(sqlDriver);

		final String updatePath = getTransformationDirectory(bc) + "InjectConnectedSegments.sql";
		updateQuery = FileUtils.readFileToString(new File(updatePath));
	}

	@Override
	public void rhs(final Collection<Long> segments) throws Exception {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = sqlDriver.getConnection().prepareStatement(updateQuery);
		}

		for (final Long segment : segments) {
			preparedUpdateStatement.setLong(1, segment);
			preparedUpdateStatement.executeUpdate();
		}
	}
}
