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

package hu.bme.mit.trainbenchmark.benchmark.ingraph.test.queryspecific;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.IngraphBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.config.IngraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.queryspecific.RouteSensorTest;

@RunWith(Parameterized.class)
public class IngraphRouteSensorTest extends RouteSensorTest {

	@Parameters(name="variant={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList("A", "B", "C");
	}

	@Parameter
	public String variant;

	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final int messageSize = 16;
		final IngraphBenchmarkConfig bc = new IngraphBenchmarkConfig(bcb, messageSize, variant, null);
		final IngraphBenchmarkScenario scenario = IngraphBenchmarkScenario.create(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
