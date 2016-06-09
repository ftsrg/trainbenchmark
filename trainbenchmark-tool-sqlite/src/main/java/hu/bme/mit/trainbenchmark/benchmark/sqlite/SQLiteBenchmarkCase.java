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

package hu.bme.mit.trainbenchmark.benchmark.sqlite;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SqlBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;

public class SQLiteBenchmarkCase extends SqlBenchmarkCase<BenchmarkConfigWrapper, SQLiteDriver> {

	@Override
	public SQLiteDriver createDriver(final BenchmarkConfigWrapper benchmarkConfigWrapper) throws Exception {
		return new SQLiteDriver();
	}

}
