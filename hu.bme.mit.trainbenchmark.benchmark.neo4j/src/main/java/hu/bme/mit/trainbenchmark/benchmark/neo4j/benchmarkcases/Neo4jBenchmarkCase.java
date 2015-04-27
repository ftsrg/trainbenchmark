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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;
import java.util.Comparator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

public class Neo4jBenchmarkCase extends AbstractBenchmarkCase<Neo4jMatch, Node> {

	protected Neo4jBenchmarkConfig nbc;

	protected GraphDatabaseService graphDb;
	protected String dbPath;

	protected Neo4jDriver neoDriver;

	@Override
	public void init() throws IOException {
		super.init();
		this.nbc = (Neo4jBenchmarkConfig) bc;

		dbPath = bc.getWorkspacePath() + "/models/neo4j-dbs/railway-database";
		driver = neoDriver = new Neo4jDriver(dbPath);
		checker = Neo4jChecker.newInstance(neoDriver, bc.getQuery());

		if (bc.getScenario() != Scenario.BATCH) {
			transformation = Neo4jTransformation.newInstance(neoDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected void destroy() throws IOException {
		driver.destroy();
	}

	@Override
	protected Comparator<?> getComparator() {
		switch (bc.getScenario()) {
		case BATCH:
		case USER:
			return driver.getElementComparator();
		case REPAIR:
			return new Neo4jMatchComparator();
		default:
			throw new UnsupportedOperationException();
		}
	}

}
