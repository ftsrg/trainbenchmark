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

package hu.bme.mit.trainbenchmark.benchmark.emfapi.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.EmfApiBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.config.EmfApiBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;

public class EmfApiTest extends TrainBenchmarkTest {

	@Override
	protected BenchmarkResult runTest(BenchmarkConfigCore bc) throws Exception {
		final EmfApiBenchmarkConfigWrapper dbcw = createDrools5BenchmarkConfigWrapper(bc);
		final EmfApiBenchmarkScenario scenario = new EmfApiBenchmarkScenario(dbcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}
	
	protected EmfApiBenchmarkConfigWrapper createDrools5BenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new EmfApiBenchmarkConfigWrapper(bc);
	}

}
