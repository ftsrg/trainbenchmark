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
package hu.bme.mit.trainbenchmark.benchmark.memsql;

import java.io.IOException;
import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.memsql.driver.MemSQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLChecker;

public class MemSQLBenchmarkCase extends SQLBenchmarkCase<BenchmarkConfig, MemSQLDriver> {

	@Override
	public MemSQLDriver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new MemSQLDriver(benchmarkConfig);
	}

	@Override
	public SQLChecker createChecker(final BenchmarkConfig benchmarkConfig, final MemSQLDriver driver) throws Exception {
		return new SQLChecker(driver, benchmarkConfig);
	}

	@Override
	public Transformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final MemSQLDriver driver) throws IOException {
		return null;
	}

	@Override
	public Comparator<?> createMatchComparator() {
		return null;
	}

}
