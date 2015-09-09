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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.DescribedBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.Neo4jModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.Neo4jModelDescription;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.Neo4jQueryAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.Neo4jCoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.Neo4jCypherChecker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;

import java.util.Comparator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

public class Neo4jBenchmarkCase extends BenchmarkCase<Neo4jMatch, Node, Neo4jDriver> implements
		AnalyzedBenchmarkCase, DescribedBenchmarkCase {

	protected Neo4jBenchmarkConfig nbc;

	protected GraphDatabaseService graphDb;
	protected String dbPath;

	protected Neo4jDriver neoDriver;

	@Override
	public void init() throws Exception {
		this.nbc = (Neo4jBenchmarkConfig) benchmarkConfig;

		dbPath = benchmarkConfig.getWorkspacePath() + "/models/neo4j-dbs/railway-database";
		driver = neoDriver = new Neo4jDriver(dbPath);
		neoDriver.setBenchmarkConfig(nbc);

		if (nbc.isJavaApi()) {
			checker = Neo4jCoreChecker.newInstance(neoDriver, benchmarkConfig.getQuery());
		} else {
			checker = Neo4jCypherChecker.newInstance(neoDriver, benchmarkConfig);
		}

		transformation = Neo4jTransformation.newInstance(neoDriver, benchmarkConfig.getQuery(),
				benchmarkConfig.getScenario());

	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new Neo4jMatchComparator();
	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = new Neo4jModelAnalyzer(neoDriver);
		queryAnalyzer = new Neo4jQueryAnalyzer(neoDriver);
	}

	@Override
	public void initDescription() {
		modelAnalyzer = new Neo4jModelDescription(neoDriver);

	}

}
