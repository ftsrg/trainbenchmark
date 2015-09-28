/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.fourstore.test;

import hu.bme.mit.trainbenchmark.benchmark.fourstore.FourStoreBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class FourStoreBenchmarkInitializer extends TestBenchmarkInitializer<FourStoreBenchmarkLogic> {

	@Override
	protected FourStoreBenchmarkLogic initializeBenchmark(final Query query, final Scenario scenario) {
		final FourStoreBenchmarkConfig fsbc = new FourStoreBenchmarkConfig(scenario, size, "FourStore", runIndex, query, iterationCount,
				transformationStrategy, transformationConstant);
		return new FourStoreBenchmarkLogic(fsbc);
	}

}
