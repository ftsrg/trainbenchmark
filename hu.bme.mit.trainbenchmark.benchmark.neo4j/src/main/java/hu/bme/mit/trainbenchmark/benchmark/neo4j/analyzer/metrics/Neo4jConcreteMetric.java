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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.ConcreteMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Iterator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public abstract class Neo4jConcreteMetric extends ConcreteMetric<Neo4jDriver> {

	protected GraphDatabaseService database;

	protected GlobalGraphOperations graphOperations;

	protected Transaction tx;

	public Neo4jConcreteMetric(Neo4jDriver driver) {
		super(driver);
		database = driver.getGraphDb();
		graphOperations = GlobalGraphOperations.at(database);
	}

	public void beginTransaction() {
		tx = database.beginTx();
	}

	public void finishTransaction() {
		tx.success();
		tx.close();
	}

	protected Iterator<Node> getNodesIterator() {
		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();
		return iterator;
	}

	public GraphDatabaseService getDatabase() {
		return database;
	}

	public void setDatabase(GraphDatabaseService database) {
		this.database = database;
	}

	public GlobalGraphOperations getGraphOperations() {
		return graphOperations;
	}

	public void setGraphOperations(GlobalGraphOperations graphOperations) {
		this.graphOperations = graphOperations;
	}

}
