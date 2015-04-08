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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;

public abstract class Neo4jBenchmarkCase extends AbstractBenchmarkCase<Neo4jMatch, Node> {

	protected Neo4jBenchmarkConfig nbc;

	protected GraphDatabaseService graphDb;
	protected String dbPath;
	protected String query;

	protected Neo4jDriver neoDriver;

	@Override
	public void init() throws IOException {
		super.init();
		this.nbc = (Neo4jBenchmarkConfig) bc;

		dbPath = bc.getWorkspacePath() + "/models/neo4j-dbs/railway-database";
		query = FileUtils.readFileToString(new File(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.neo4j/src/main/resources/queries/" + getName() + ".cypher"));
	}

	@Override
	public void read() throws FileNotFoundException, IOException {
		driver = neoDriver = new Neo4jDriver(dbPath, query);
		neoDriver.read(bc.getModelPathNameWithoutExtension() + ".graphml");

		graphDb = neoDriver.getGraphDb();
	}

	@Override
	public Collection<Neo4jMatch> check() throws IOException {
		if (nbc.isJavaApi()) {
			matches = checkJava();
		} else {
			matches = neoDriver.runQuery(getName());
		}

		return matches;
	}

	protected abstract Collection<Neo4jMatch> checkJava();

	@Override
	protected void destroy() throws IOException {
		driver.destroy();
	}

}
