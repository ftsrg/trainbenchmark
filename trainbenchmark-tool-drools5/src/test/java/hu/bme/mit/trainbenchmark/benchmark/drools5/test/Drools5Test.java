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

package hu.bme.mit.trainbenchmark.benchmark.drools5.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.drools5.Drools5BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.drools5.config.Drools5BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.BenchmarkBaseTest;

public class Drools5Test extends BenchmarkBaseTest {

	@Override
	protected BenchmarkResult runTest() throws Exception {
		final Drools5BenchmarkConfigWrapper dbcw = createDrools5BenchmarkConfigWrapper(bc);
		final Drools5BenchmarkScenario scenario = new Drools5BenchmarkScenario(dbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}
	
	protected Drools5BenchmarkConfigWrapper createDrools5BenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new Drools5BenchmarkConfigWrapper(bc);
	}

}
