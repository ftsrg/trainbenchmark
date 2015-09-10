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

import javax.naming.SizeLimitExceededException;

public class Cluster {

	public static int numberOfNodes = 5;

	public static int maxID;

	protected static int id = 0;

	protected List<Node> diagonals;

	protected Node center;

	protected List<Cluster> subClusters;

	public Cluster() {
		diagonals = new ArrayList<Node>();
		subClusters = new ArrayList<Cluster>();
	}

	public void build() throws SizeLimitExceededException {
		center = createNode();
		for (int i = 0; i < numberOfNodes - 1; i++) {
			try {
				diagonals.add(createNode());
			} catch (SizeLimitExceededException e) {
				for (Node source : diagonals) {
					for (Node target : diagonals) {
						if (source != target) {
							source.conn.add(target.id);
							source.conn.add(center.id);
						}
					}
					center.conn.add(source.id);
				}
				return;
			}
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

	public Cluster copy() throws SizeLimitExceededException {
		Cluster newCluster = new Cluster();
		newCluster.build();
		Cluster newSubCluster;
		for (Cluster c : subClusters) {
			try {
				newSubCluster = c.copy();
			} catch (SizeLimitExceededException e) {
				return newCluster;
			}
			newCluster.subClusters.add(newSubCluster);
			if (newSubCluster.subClusters.isEmpty()) {
				drawEdges(newSubCluster, newCluster.center);
			} else {
				List<Cluster> deepestClusters = new ArrayList<Cluster>();
				newSubCluster.getDeepestClusters(deepestClusters);
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

	public void getDeepestClusters(final List<Cluster> deepestClusters) {
		if (subClusters == null) {
			deepestClusters.add(this);
		}
		if (subClusters.isEmpty()) {
			deepestClusters.add(this);
		}
		for (Cluster c : subClusters) {
			c.getDeepestClusters(deepestClusters);
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

	public Node createNode() throws SizeLimitExceededException {
		return createNode(0);
	}

	public Node createNode(int degree) throws SizeLimitExceededException {
		if (id >= maxID) {
			throw new SizeLimitExceededException(
					"The maximum number of identifiers are exceeded: " + maxID);
		}
		Node n = new Node(degree, id);
		id++;
		return n;
	}
}
