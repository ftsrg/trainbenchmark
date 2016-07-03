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

package hu.bme.mit.trainbenchmark.benchmark.sqlite.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.SQLiteBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkBaseTest;

public class SQLiteTest extends BenchmarkBaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final SQLiteBenchmarkConfigWrapper sbcw = createSQLiteBenchmarkConfigWrapper(bc);
		final SQLiteBenchmarkScenario scenario = new SQLiteBenchmarkScenario(sbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

	protected SQLiteBenchmarkConfigWrapper createSQLiteBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new SQLiteBenchmarkConfigWrapper(bc);
	}

}
