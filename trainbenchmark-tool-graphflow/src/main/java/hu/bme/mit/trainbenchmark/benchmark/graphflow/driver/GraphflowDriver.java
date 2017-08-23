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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.comparators.NodeComparator;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public class GraphflowDriver extends Driver {

	protected Transaction tx;
	protected GraphDatabaseService graphDb;
	protected final Comparator<Node> nodeComparator = new NodeComparator();
	protected final File databaseDirectory;

	public GraphflowDriver(final String modelDir) throws IOException {
		super();
		this.databaseDirectory = new File(modelDir + "/neo4j-dbs/railway-database");
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();

		// delete old database directory
		if (databaseDirectory.exists()) {
			FileUtils.deleteDirectory(databaseDirectory);
		}
	}

	@Override
	public void destroy() {
		if (graphDb != null) {
			graphDb.shutdown();
		}
	}

	@Override
	public void beginTransaction() {
		tx = graphDb.beginTx();
	}

	@Override
	public void finishTransaction() {
		tx.success();
		tx.close();
	}

	@Override
	public void read(final String modelPath) {
		// TODO
	}

	@Override
	public String getPostfix() {
		return Neo4jConstants.QUERY_EXTENSION;
	}

	public Collection<GraphflowMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		final Collection<GraphflowMatch> results = new ArrayList<>();

		// TODO

		return results;
	}

	public void runTransformation(final String transformationDefinition, final Map<String, Object> parameters)
			throws IOException {
		// TODO
	}

}
