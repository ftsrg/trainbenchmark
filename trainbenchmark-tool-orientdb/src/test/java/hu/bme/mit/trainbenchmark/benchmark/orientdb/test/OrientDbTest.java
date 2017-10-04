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

package hu.bme.mit.trainbenchmark.benchmark.orientdb.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.OrientDbBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.config.OrientDbBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.config.OrientDbBenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphEngine;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@Ignore
@RunWith(Parameterized.class)
public class OrientDbTest extends TrainBenchmarkTest {

	@Parameterized.Parameters(name = "engine={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(
			TinkerGraphEngine.CORE_API,
			TinkerGraphEngine.GREMLIN
		);
	}

	@Parameterized.Parameter(value = 0)
	public TinkerGraphEngine engine;

	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final OrientDbBenchmarkConfig bc = new OrientDbBenchmarkConfigBuilder().setConfigBase(bcb)
			.setEngine(engine).createConfig();
		final OrientDbBenchmarkScenario scenario = new OrientDbBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
