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
import java.util.Comparator;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.Neo4jCoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.Neo4jCypherChecker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;
import hu.bme.mit.trainbenchmark.constants.Query;

public class Neo4jBenchmarkCase<Neo4jChecker>
		extends AbstractBenchmarkCase<Neo4jMatch, Node, Neo4jDriver, Neo4jBenchmarkConfig, Checker<Neo4jMatch>> {

	@Override
	public Neo4jDriver createDriver(final Neo4jBenchmarkConfig benchmarkConfig) throws Exception {
		return new Neo4jDriver(benchmarkConfig);
	}
	
	@Override
	public Checker<Neo4jMatch> createChecker(final Neo4jBenchmarkConfig benchmarkConfig, final Neo4jDriver driver, final Query query)
			throws Exception {
		if (benchmarkConfig.isCoreApi()) {
			return Neo4jCoreChecker.newInstance(driver, query);
		} else {
			return Neo4jCypherChecker.newInstance(driver, benchmarkConfig, query);
		}
	}

	@Override
	public Comparator<?> createMatchComparator() {
		return new Neo4jMatchComparator();
	}

	@Override
	public Transformation<?, ?> createTransformation(final Neo4jBenchmarkConfig benchmarkConfig, final Neo4jDriver driver, final Query query)
			throws IOException {
		return Neo4jTransformation.newInstance(driver, query, benchmarkConfig.getScenario());
	}

}
