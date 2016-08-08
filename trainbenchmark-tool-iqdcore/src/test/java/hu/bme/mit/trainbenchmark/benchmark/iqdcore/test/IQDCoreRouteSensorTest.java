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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore.test;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.trainbenchmark.benchmark.iqdcore.IQDCoreBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDCoreBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.queryspecific.RouteSensorTest;

@RunWith(Parameterized.class)
public class IQDCoreRouteSensorTest extends RouteSensorTest {

	@Parameters
	public static Iterable<? extends Object> data() {
		return Arrays.asList("A", "B", "C");
	}

	@Parameter
	public String variant;

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final int messageSize = 16;
		System.out.println();
		final IQDCoreBenchmarkConfigWrapper config = new IQDCoreBenchmarkConfigWrapper(bc, messageSize, variant, null);
		final IQDCoreBenchmarkScenario scenario = IQDCoreBenchmarkScenario.create(config);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
