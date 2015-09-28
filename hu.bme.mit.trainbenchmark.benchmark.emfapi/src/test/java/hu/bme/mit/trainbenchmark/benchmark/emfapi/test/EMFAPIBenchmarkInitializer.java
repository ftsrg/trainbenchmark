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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.EMFAPIBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class EMFAPIBenchmarkInitializer extends TestBenchmarkInitializer<EMFAPIBenchmarkLogic> {

	@Override
	protected EMFAPIBenchmarkLogic initializeBenchmark(final Query query, final Scenario scenario) {
		final BenchmarkConfig bc = new BenchmarkConfig("EMFAPI", scenario, size, 1, query, iterationCount,
				transformationStrategy, transformationConstant);
		return new EMFAPIBenchmarkLogic(bc);
	}

}
