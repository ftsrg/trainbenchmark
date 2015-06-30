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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.test.cypher;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.Neo4jBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class Neo4jCypherBenchmarkInitializer extends TestBenchmarkInitializer<Neo4jBenchmarkLogic> {

	@Override
	protected Neo4jBenchmarkLogic initializeBenchmark(final Query query, final Scenario scenario) {
		final Neo4jBenchmarkConfig nbc = new Neo4jBenchmarkConfig(scenario, size, 1, query, iterationCount, modificationMethod,
				modificationConstant, false);
		return new Neo4jBenchmarkLogic(nbc);
	}

}
