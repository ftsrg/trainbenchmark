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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpanders;
import org.neo4j.graphdb.Relationship;
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

	@Override
	public void calculateMetrics() {
		database = driver.getGraphDb();
		graphOperations = GlobalGraphOperations.at(database);
		double currentDegree = 0;

		beginTransaction();

		ResourceIterable<Node> nodes = graphOperations.getAllNodes();
		Iterator<Node> iterator = nodes.iterator();

		Node node;
		List<Long> stations = new ArrayList<>();
		while (iterator.hasNext()) {
			numberOfNodes++;

			node = iterator.next();

			for (Label l : node.getLabels()) {
				if (l.name().equals(STATION)) {
					stations.add(node.getId());
				}
			}

			currentDegree = node.getDegree(Direction.BOTH);
			changeMaximumDegree(EdgeDirection.BOTH, currentDegree);

			currentDegree = node.getDegree(Direction.OUTGOING);
			determineClustering(node);

			if (currentDegree > 0) {
				numberOfNodesWithOutgoingDegrees++;
			}
			changeMaximumDegree(EdgeDirection.OUTGOING, currentDegree);
			numberOfEdges += currentDegree;
		}

		calculateShortestPaths(stations);

		calculateNumberOfDegrees(nodes);

		finishTransaction();
	}

	private void determineClustering(final Node node) {
		Iterable<Relationship> relations = node.getRelationships(Direction.OUTGOING);
		// use Set to avoid storing the same node more than one
		Set<Node> neighbors = new HashSet<>();

		for (Relationship relationship : relations) {
			neighbors.add(relationship.getEndNode());
		}

		int connected = 0;
		for (Node n : neighbors) {
			for (Relationship r : n.getRelationships(Direction.OUTGOING)) {
				Node end = r.getEndNode();
				if (end != n && neighbors.contains(end)) {
					connected++;
				}
			}
		}
		for (Label l : node.getLabels()) {
			if (l.name().equals(STATION)) {
				addClusteringCoefficient(connected, neighbors.size(), STATION);
				return;
			}
		}
		addClusteringCoefficient(connected, neighbors.size());
	}

	private void calculateShortestPaths(List<Long> stations) {
		if (stations.size() == 0) {
			return;
		}
		Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
		long sourceID;
		long targetID;
		Node sourceNode;
		Node targetNode;
		int i = 0;
		while (i < shortestPathMetric.getPairs()) {
			sourceID = stations.get(random.nextInt(stations.size()));
			targetID = stations.get(random.nextInt(stations.size()));
			if (sourceID != targetID) {
				sourceNode = database.getNodeById(sourceID);
				targetNode = database.getNodeById(targetID);
				PathFinder<Path> finder = GraphAlgoFactory.shortestPath(
						PathExpanders.forDirection(Direction.OUTGOING),
						shortestPathMetric.getMaxDepth());
				Path path = finder.findSinglePath(sourceNode, targetNode);
				if (path != null) {
					shortestPathMetric.add(path.length());
					i++;
				}
			}

		}
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
			changeNumberOfDegrees(EdgeDirection.OUTGOING, currentDegree, roundedOutgoingDegree);
		}
	}

}
