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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.test;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EMFIncQueryBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class EMFIncQueryBenchmarkInitializer extends TestBenchmarkInitializer<EMFIncQueryBenchmarkLogic> {

	@Override
	protected EMFIncQueryBenchmarkLogic initializeBenchmark(final String queryName, final Scenario scenario) {
		final EMFIncQueryBenchmarkConfig eiqbc = new EMFIncQueryBenchmarkConfig(scenario, size, "EMFIncQuery", runIndex, queryName,
				iterationCount, modificationMethod, modificationConstant);
		return new EMFIncQueryBenchmarkLogic(eiqbc);
	}

}
