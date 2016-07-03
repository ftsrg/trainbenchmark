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

package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkBaseTest;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.TinkerGraphBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfigWrapper;

public class TinkerGraphTest extends BenchmarkBaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final TinkerGraphBenchmarkConfigWrapper dbcw = createTinkerGraphBenchmarkConfigWrapper(bc);
		final TinkerGraphBenchmarkScenario scenario = new TinkerGraphBenchmarkScenario(dbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}
	
	protected TinkerGraphBenchmarkConfigWrapper createTinkerGraphBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new TinkerGraphBenchmarkConfigWrapper(bc);
	}

}
