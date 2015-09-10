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

package hu.bme.mit.trainbenchmark.generator.concretes.schedule;

import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.util.Cluster;
import hu.bme.mit.trainbenchmark.generator.util.Node;

import java.io.IOException;
import java.util.List;

public class HierarchicalSheduleGenerator extends HomogeneousScheduleGenerator {

	protected int clusterSize;
	protected Cluster rootCluster;
	protected int maxIterations;

	public HierarchicalSheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate hierarchical schedule model...");
	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		clusterSize = 5;
		maxIterations = 5;
		Cluster.numberOfNodes = clusterSize;
	}

	@Override
	protected void initializationStep() throws IOException {
		rootCluster = new Cluster();
		rootCluster.build();
	}

	@Override
	protected void generateStations() throws IOException {
		for (int i = 0; i < maxIterations; i++) {
			buildClusters(i);
		}

		List<Node> nodes = rootCluster.getNodes();
		for (Node node : nodes) {
			addStation();
			node.obj = stations.get(lastSt());
		}

		for (Node node : nodes) {
			for (Integer target : node.conn) {
				addNeighbor(node.id, target);
			}
		}

	}

	protected void buildClusters(final int iteration) {
		Cluster copyCluster1 = rootCluster.copy();
		Cluster copyCluster2 = rootCluster.copy();
		Cluster copyCluster3 = rootCluster.copy();
		Cluster copyCluster4 = rootCluster.copy();
		rootCluster.addSubCluster(copyCluster1, iteration);
		rootCluster.addSubCluster(copyCluster2, iteration);
		rootCluster.addSubCluster(copyCluster3, iteration);
		rootCluster.addSubCluster(copyCluster4, iteration);

	}

	@Override
	protected int getNeighborsNumber() {
		return 0;
	}

}
