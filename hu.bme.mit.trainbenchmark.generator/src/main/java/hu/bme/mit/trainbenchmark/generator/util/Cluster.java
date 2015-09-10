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

package hu.bme.mit.trainbenchmark.generator.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cluster {

	public static int numberOfNodes = 5;

	public static int id = 0;

	public static int maxID;

	public List<Node> diagonals;

	public Node center;

	public List<Cluster> subClusters;

	public Cluster() {
		diagonals = new ArrayList<Node>();
		subClusters = new ArrayList<Cluster>();
	}

	public void build() {
		center = createNode();
		for (int i = 0; i < numberOfNodes - 1; i++) {
			diagonals.add(createNode());
		}
		for (Node source : diagonals) {
			for (Node target : diagonals) {
				if (source != target) {
					source.conn.add(target.id);
					source.conn.add(center.id);
				}
			}
			center.conn.add(source.id);
		}
	}

	public Cluster copy(final int maxDepth) {
		return copy(maxDepth, 0);
	}

	public Cluster copy(final int maxDepth, int iteration) {
		Cluster newCluster = new Cluster();
		newCluster.build();
		Cluster newSubCluster;
		iteration++;
		for (Cluster c : subClusters) {
			newSubCluster = c.copy(maxDepth);
			newCluster.subClusters.add(newSubCluster);
			if (newSubCluster.subClusters.isEmpty()) {
				drawEdges(newSubCluster, newCluster.center);
			} else {
				List<Cluster> deepestClusters = new ArrayList<Cluster>();
				newSubCluster.getDeepestClusters(deepestClusters, iteration, maxDepth);
				for (Cluster cl : deepestClusters) {
					drawEdges(cl, newCluster.center);
				}

			}

		}
		return newCluster;

	}

	public void drawEdges(Cluster cluster, Node center) {
		for (Node n : cluster.diagonals) {
			n.conn.add(center.id);
			center.conn.add(n.id);
		}
	}

	public void addSubCluster(final Cluster cluster, int maxDepth) {
		List<Cluster> deepestClusters = new ArrayList<Cluster>();
		cluster.getDeepestClusters(deepestClusters, 0, maxDepth);
		for (Cluster cl : deepestClusters) {
			drawEdges(cl, center);
		}
		subClusters.add(cluster);
	}

	public void getDeepestClusters(final List<Cluster> deepestClusters, int depth, final int maxDepth) {
		if (subClusters == null && depth == maxDepth) {
			deepestClusters.add(this);
		}
		if (subClusters.isEmpty() && depth == maxDepth) {
			deepestClusters.add(this);
		}
		depth++;
		for (Cluster c : subClusters) {
			if (depth == maxDepth) {
				deepestClusters.add(c);
			} else {
				c.getDeepestClusters(deepestClusters, depth, maxDepth);
			}
		}
	}

	public List<Node> getNodes() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(center);
		nodes.addAll(diagonals);
		for (Cluster cluster : subClusters) {
			nodes.addAll(cluster.getNodes());
		}
		Collections.sort(nodes);
		return nodes;

	}

	public Node createNode() {
		return createNode(0);
	}

	public Node createNode(int degree) {
		Node n = new Node(degree, id);
		id++;
		return n;
	}
}
