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

package hu.bme.mit.trainbenchmark.benchmark.jena.noinferencing.test;

import hu.bme.mit.trainbenchmark.benchmark.jena.JenaBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class JenaBenchmarkNoInferencingInitializer extends TestBenchmarkInitializer {

	@Override
	protected BenchmarkLogic initializeBenchmark(final Query query, final Scenario scenario) {
		final RDFBenchmarkConfig rbc = new RDFBenchmarkConfig("Jena", scenario, size, 1, query, iterationCount, transformationStrategy,
				transformationConstant, false);
		return new BenchmarkLogic(rbc, new JenaBenchmarkCase());
	}

}
