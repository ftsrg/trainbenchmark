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

package hu.bme.mit.trainbenchmark.benchmark.neo4j;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;

public class Neo4jBenchmarkMain {

	// https://graphaware.com/neo4j/transactions/2014/07/11/neo4j-transaction-event-api.html

	public static void main(final String[] args) throws Exception {
		final Neo4jBenchmarkConfig bc = BenchmarkConfig.fromFile(args[0], Neo4jBenchmarkConfig.class);
		final Neo4jBenchmarkScenario scenario = new Neo4jBenchmarkScenario(bc);
		scenario.performBenchmark();
	}

}
