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

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.Neo4jBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;

@RunWith(Parameterized.class)
public class Neo4jTest extends TrainBenchmarkTest {

	@Parameters(name="engine={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(Neo4jEngine.CYPHER, Neo4jEngine.COREAPI);
	}

	@Parameter
	public Neo4jEngine engine;
	
	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcc) throws Exception {
		final Neo4jBenchmarkConfig bc = new Neo4jBenchmarkConfig(bcc, engine);
		final Neo4jBenchmarkScenario scenario = new Neo4jBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

}
