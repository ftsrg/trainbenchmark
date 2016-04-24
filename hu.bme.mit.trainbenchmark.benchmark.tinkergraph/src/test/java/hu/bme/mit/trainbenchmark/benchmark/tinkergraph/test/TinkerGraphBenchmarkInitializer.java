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

import java.util.Arrays;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkRunner;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.TinkerGraphBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public class TinkerGraphBenchmarkInitializer extends TestBenchmarkInitializer {
	
	public TinkerGraphBenchmarkInitializer() {
	}

	public static Collection<Object[]> getTestParameters() {
		return Arrays.asList(new Object[][] { { } });
	}
	
	@Override
	protected BenchmarkRunner initializeBenchmark(final RailwayQuery query, final ScenarioEnum scenario) {
		final TinkerGraphBenchmarkConfig rbc = new TinkerGraphBenchmarkConfig(scenario, size, runIndex, query, iterationCount, transformationStrategy,
				transformationConstant);
		return new BenchmarkRunner(rbc, new TinkerGraphBenchmarkCase());
	}

}
