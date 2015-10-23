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

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SQLTransformation;
import hu.bme.mit.trainbenchmark.constants.Query;

public class SQLTransformationInject extends SQLTransformation<Long> {

	public SQLTransformationInject(final SQLDriver driver, final BenchmarkConfig benchmarkConfig, final Query query) throws IOException {
		super(driver, benchmarkConfig, query);
	}

	@Override
	public void rhs(final Collection<Long> elements) throws SQLException {
		if (preparedUpdateStatement == null) {
			preparedUpdateStatement = driver.getConnection().prepareStatement(updateQuery);
		}

		for (final Long element : elements) {
			preparedUpdateStatement.setLong(1, element);
			preparedUpdateStatement.executeUpdate();
		}
	}
	
}
