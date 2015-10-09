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

public class Neo4jBenchmarkCase<Neo4jChecker>
		extends AbstractBenchmarkCase<Neo4jMatch, Node, Neo4jDriver, Neo4jBenchmarkConfig, Checker<Neo4jMatch>> {

	@Override
	public Neo4jDriver createDriver(final Neo4jBenchmarkConfig benchmarkConfig) throws Exception {
		final String dbPath = benchmarkConfig.getWorkspacePath() + "/models/neo4j-dbs/railway-database";
		return new Neo4jDriver(dbPath);
	}

	@Override
	public Checker<Neo4jMatch> createChecker(final Neo4jBenchmarkConfig benchmarkConfig, final Neo4jDriver driver) throws Exception {
		if (benchmarkConfig.isCoreApi()) {
			return Neo4jCoreChecker.newInstance(driver, benchmarkConfig.getQuery());
		} else {
			return Neo4jCypherChecker.newInstance(driver, benchmarkConfig);
		}
	}

	@Override
	public Transformation<?> createTransformation(final Neo4jBenchmarkConfig benchmarkConfig, final Neo4jDriver driver) throws IOException {
		return Neo4jTransformation.newInstance(driver, benchmarkConfig.getQuery(), benchmarkConfig.getScenario());
	}

	@Override
	public Comparator<?> createMatchComparator() {
		return new Neo4jMatchComparator();
	}

}
