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

package hu.bme.mit.trainbenchmark.benchmark.mysql.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.mysql.MySqlBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;

public class MySqlTest extends TrainBenchmarkTest {

	@Override
	protected BenchmarkResult runTest(BenchmarkConfigCore bc) throws Exception {
		final MySqlBenchmarkConfigWrapper msbcw = createMySqlBenchmarkConfigWrapper(bc);
		final MySqlBenchmarkScenario scenario = new MySqlBenchmarkScenario(msbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

	protected MySqlBenchmarkConfigWrapper createMySqlBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new MySqlBenchmarkConfigWrapper(bc);
	}


}
