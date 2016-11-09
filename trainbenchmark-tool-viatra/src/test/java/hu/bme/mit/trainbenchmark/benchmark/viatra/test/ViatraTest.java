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

package hu.bme.mit.trainbenchmark.benchmark.viatra.test;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import hu.bme.mit.trainbenchmark.benchmark.viatra.ViatraBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBenchmarkConfigBuilder;

@RunWith(Parameterized.class)
public class ViatraTest extends TrainBenchmarkTest {

	@Parameters(name = "backend={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(ViatraBackend.INCREMENTAL, ViatraBackend.LOCAL_SEARCH);
	}

	@Parameter
	public ViatraBackend backend;

	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final ViatraBenchmarkConfig bc = new ViatraBenchmarkConfigBuilder().setConfigBase(bcb).setBackend(backend)
				.createBenchmarkConfig();
		final ViatraBenchmarkScenario scenario = new ViatraBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
