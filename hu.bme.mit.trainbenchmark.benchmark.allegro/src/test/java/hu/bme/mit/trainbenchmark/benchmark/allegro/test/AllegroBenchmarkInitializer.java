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

package hu.bme.mit.trainbenchmark.benchmark.allegro.test;

import hu.bme.mit.trainbenchmark.benchmark.allegro.AllegroBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkRunner;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public class AllegroBenchmarkInitializer extends TestBenchmarkInitializer {

	@Override
	protected BenchmarkRunner initializeBenchmark(final RailwayQuery query, final ScenarioEnum scenario) {
		final RDFBenchmarkConfig rbc = new RDFBenchmarkConfig("Allegro", scenario, size, runIndex, query, iterationCount,
				transformationStrategy, transformationConstant, false);
		return new BenchmarkRunner(rbc, new AllegroBenchmarkCase());
	}

}
