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

package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.config.EclipseOclBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;

public class EclipseOclTest extends TrainBenchmarkTest {

	@Override
	protected BenchmarkResult runTest(BenchmarkConfigCore bc) throws Exception {
		final EclipseOclBenchmarkConfigWrapper eobcw = createEclipseOclBenchmarkConfigWrapper(bc);
		final EclipseOclBenchmarkScenario scenario = new EclipseOclBenchmarkScenario(eobcw);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}
	
	protected EclipseOclBenchmarkConfigWrapper createEclipseOclBenchmarkConfigWrapper(final BenchmarkConfigCore bc) {
		return new EclipseOclBenchmarkConfigWrapper(bc);
	}

}
