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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore.noinferencing.test;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.IQDBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.test.BaseTest;

public class IQDCoreBatchNoInferencingTest extends BaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final int messageSize = 16;
		final IQDConfigWrapper config = new IQDConfigWrapper(bc, messageSize);
		final IQDBenchmarkScenario scenario = IQDBenchmarkScenario.create(config);
		final BenchmarkResult result = scenario.runBenchmark();
		return result;
	}
}
