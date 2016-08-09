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
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;

public class Drools5Test extends TrainBenchmarkTest {

	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigCore bc) throws Exception {
		final Drools5BenchmarkConfigWrapper bcw = new Drools5BenchmarkConfigWrapper(bc);
		final Drools5BenchmarkScenario scenario = new Drools5BenchmarkScenario(bcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
