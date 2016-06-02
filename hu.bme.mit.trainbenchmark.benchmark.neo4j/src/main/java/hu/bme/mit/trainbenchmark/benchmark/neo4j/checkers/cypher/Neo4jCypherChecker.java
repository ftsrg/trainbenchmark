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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers.cypher;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Neo4jCypherChecker extends ModelQuery<Neo4jMatch, Neo4jDriver> {

	protected final RailwayQuery query;
	protected final String queryDefinition;

	protected Neo4jCypherChecker(final Neo4jDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		super(driver);

		this.query = query;
		queryDefinition = FileUtils.readFileToString(new File(benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.neo4j/src/main/resources/queries/" + query + ".cyp"));
	}

	@Override
	public Collection<Neo4jMatch> check() throws IOException {
		return driver.runQuery(query, queryDefinition);
	}

	public static ModelQuery<Neo4jMatch, Neo4jDriver> newInstance(final Neo4jDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		return new Neo4jCypherChecker(driver, benchmarkConfig, query);
	}

}
