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

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.BenchmarkLogic;

public class Neo4jBenchmarkMain {

	public static void main(final String[] args) throws IOException, ParseException {
		final Neo4jBenchmarkConfig bc = new Neo4jBenchmarkConfig(args);
		final BenchmarkLogic benchmarkLogic = new BenchmarkLogic(bc, new Neo4jBenchmarkCase());
		benchmarkLogic.runBenchmark();
	}

}
