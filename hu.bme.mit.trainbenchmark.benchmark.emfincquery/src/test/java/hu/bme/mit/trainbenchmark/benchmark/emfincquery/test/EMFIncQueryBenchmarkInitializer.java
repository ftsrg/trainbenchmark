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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.test;

import java.util.Arrays;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EMFIncQueryBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkRunner;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class EMFIncQueryBenchmarkInitializer extends TestBenchmarkInitializer {

	protected boolean localSearch;

	public EMFIncQueryBenchmarkInitializer(boolean localSearch) {
		this.localSearch = localSearch;
	}
	
	public static List<Object[]> getTestParameters() {
		return Arrays.asList(new Object[][] { { true }, { false } });
	}

	@Override
	protected BenchmarkRunner initializeBenchmark(final Query query, final Scenario scenario) {
		final EMFIncQueryBenchmarkConfig benchmarkConfig = new EMFIncQueryBenchmarkConfig(scenario, size, 1, query,
				iterationCount, transformationStrategy, transformationConstant, localSearch);
		return new BenchmarkRunner(benchmarkConfig, new EMFIncQueryBenchmarkCase());
	}

}
