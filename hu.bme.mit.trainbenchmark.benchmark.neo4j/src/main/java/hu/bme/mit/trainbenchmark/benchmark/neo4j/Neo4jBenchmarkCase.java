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

import java.util.Comparator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.Neo4jCoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.Neo4jCypherChecker;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;

public class Neo4jBenchmarkCase extends AbstractBenchmarkCase<Neo4jMatch, Node> {

	protected GraphDatabaseService graphDb;
	protected String dbPath;

	protected Neo4jDriver neoDriver;

	@Override
	public void initialize() throws Exception {
		super.initialize();

		dbPath = bc.getWorkspacePath() + "/models/neo4j-dbs/railway-database";
		driver = neoDriver = new Neo4jDriver(dbPath);

		final Neo4jBenchmarkConfig nbc = (Neo4jBenchmarkConfig) bc;
		if (nbc.isCoreApi()) {
			checker = Neo4jCoreChecker.newInstance(neoDriver, bc.getQuery());
		} else {
			checker = Neo4jCypherChecker.newInstance(neoDriver, bc);
		}

		if (bc.getScenario().hasTranformation()) {
			transformation = Neo4jTransformation.newInstance(neoDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new Neo4jMatchComparator();
	}

}
