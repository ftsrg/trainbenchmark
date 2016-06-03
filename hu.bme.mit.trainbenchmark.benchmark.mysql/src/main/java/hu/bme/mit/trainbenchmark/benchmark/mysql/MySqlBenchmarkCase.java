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

package hu.bme.mit.trainbenchmark.benchmark.mysql;

import java.io.IOException;
import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SqlBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SqlModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class MySqlBenchmarkCase extends SqlBenchmarkCase<BenchmarkConfig, MySqlDriver> {


	@Override
	public MySqlDriver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new MySqlDriver(benchmarkConfig.getMaxMemory());
	}

	@Override
	public SqlModelQuery createModelQuery(final BenchmarkConfig benchmarkConfig, final MySqlDriver driver, final RailwayQuery query) throws Exception {
		return new SqlModelQuery(driver, benchmarkConfig, query);
	}

	@Override
	public ModelTransformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final MySqlDriver driver, final RailwayQuery query) throws IOException {
		return SqlTransformation.newInstance(driver, benchmarkConfig, query);
	}

	@Override
	public Comparator<?> getMatchComparator() {
		return new LongMatchComparator();
	}

}
