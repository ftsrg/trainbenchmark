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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelDescription;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Iterator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jModelDescription extends ModelDescription<Neo4jDriver> {

	protected GraphDatabaseService database;

	protected GlobalGraphOperations graphOperations;

	protected Transaction tx;

	public Neo4jModelDescription(Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void calculateMetrics() {
		database = driver.getGraphDb();
		graphOperations = GlobalGraphOperations.at(database);

		tx = database.beginTx();

		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();

		Node node;
		int degree;
		double count;
		while (iterator.hasNext()) {
			node = iterator.next();
			degree = node.getDegree();
			if (degreeDistributions.containsKey(degree)) {
				count = degreeDistributions.get(degree);
				count++;
				degreeDistributions.replace(degree, count);
			} else {
				degreeDistributions.put(degree, 1.0);
			}
		}
		tx.success();
		tx.close();

	}

}
