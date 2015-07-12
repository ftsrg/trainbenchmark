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

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;

import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jModelAnalyzer extends ModelAnalyzer<Neo4jDriver> {

	protected GraphDatabaseService database;

	protected GlobalGraphOperations graphOperations;

	protected Transaction tx;

	public Neo4jModelAnalyzer(Neo4jDriver driver) {
		super(driver);
	}

	public void beginTransaction() {
		tx = database.beginTx();
	}

	public void finishTransaction() {
		tx.success();
		tx.close();
	}

	public GraphDatabaseService getDatabase() {
		return database;
	}

	public GlobalGraphOperations getGraphOperations() {
		return graphOperations;
	}

	@Override
	public void calculateMetrics() {
		database = driver.getGraphDb();
		graphOperations = GlobalGraphOperations.at(database);
		numberOfNodes = 0;
		numberOfNodesWithOutgoingDegrees = 0;
		numberOfEdges = 0;
		numberOfAverageDegree = 0;
		numberOfMaximumDegree = 0;
		double currentDegree = 0;

		beginTransaction();

		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();

		Node node;
		while (iterator.hasNext()) {
			numberOfNodes++;

			node = iterator.next();

			currentDegree = node.getDegree(Direction.BOTH);
			changeMaximumDegree(EdgeDirection.BOTH, currentDegree);

			currentDegree = node.getDegree(Direction.OUTGOING);
			if (currentDegree > 0) {
				numberOfNodesWithOutgoingDegrees++;
			}
			changeMaximumDegree(EdgeDirection.OUTGOING, currentDegree);
			numberOfEdges += currentDegree;
		}

		calculateNumberOfDegrees(nodes);

		finishTransaction();
	}

	private void calculateNumberOfDegrees(ResourceIterable<Node> nodes) {
		double currentDegree;
		Iterator<Node> iterator;
		Node node;

		calculateAverageDegree(EdgeDirection.BOTH);
		int roundedDegree = roundAverageDegree(EdgeDirection.BOTH);

		calculateAverageDegree(EdgeDirection.OUTGOING);
		int roundedOutgoingDegree = roundAverageDegree(EdgeDirection.OUTGOING);

		iterator = nodes.iterator();
		while (iterator.hasNext()) {
			node = iterator.next();
			currentDegree = node.getDegree(Direction.BOTH);
			changeNumberOfDegrees(EdgeDirection.BOTH, currentDegree, roundedDegree);

			currentDegree = node.getDegree(Direction.OUTGOING);
			changeNumberOfDegrees(EdgeDirection.OUTGOING, currentDegree,
					roundedOutgoingDegree);
		}
	}

}
