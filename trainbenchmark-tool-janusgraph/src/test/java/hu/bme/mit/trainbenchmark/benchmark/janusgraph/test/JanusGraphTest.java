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

package hu.bme.mit.trainbenchmark.benchmark.janusgraph.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import hu.bme.mit.trainbenchmark.benchmark.janusgraph.JanusGraphBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.janusgraph.config.JanusGraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.janusgraph.config.JanusGraphBenchmarkConfigBuilder;

public class JanusGraphTest extends TrainBenchmarkTest {

	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final JanusGraphBenchmarkConfig bc = new JanusGraphBenchmarkConfigBuilder().setConfigBase(bcb)
				.createConfig();
		final JanusGraphBenchmarkScenario scenario = new JanusGraphBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
