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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.test;

import java.util.Arrays;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.Neo4jBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkRunner;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;

public class Neo4jBenchmarkInitializer extends TestBenchmarkInitializer {
	
	protected Neo4jEngine engine;

	public Neo4jBenchmarkInitializer(final Neo4jEngine engine) {
		this.engine = engine;
	}

	public static Collection<Object[]> getTestParameters() {
		return Arrays.asList(new Object[][] { { Neo4jEngine.COREAPI }, { Neo4jEngine.CYPHER } });
	}
	
	@Override
	protected BenchmarkRunner initializeBenchmark(final RailwayQuery query, final ScenarioEnum scenario) {
		final Neo4jBenchmarkConfig rbc = new Neo4jBenchmarkConfig(scenario, size, runIndex, query, iterationCount, transformationStrategy,
				transformationConstant, engine);
		return new BenchmarkRunner(rbc, new Neo4jBenchmarkCase());
	}

}
