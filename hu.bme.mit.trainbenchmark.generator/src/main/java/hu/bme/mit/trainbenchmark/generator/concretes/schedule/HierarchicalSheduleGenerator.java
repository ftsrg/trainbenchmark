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
import java.util.ArrayList;
import java.util.List;

import javax.naming.SizeLimitExceededException;

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
		maxIterations = 2;
		Cluster.numberOfNodes = clusterSize;
		Cluster.maxID = maxNumberOfStations;
	}

	@Override
	protected void initializationStep() throws IOException {
		rootCluster = new Cluster();
		try {
			rootCluster.build();
		} catch (SizeLimitExceededException e) {
			throw new RuntimeException(e);
		}
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
		List<Cluster> copiedClusters = new ArrayList<Cluster>();
		for (int i = 0; i < 4; i++) {
			try {
				copiedClusters.add(rootCluster.copy());
			} catch (SizeLimitExceededException e) {
				break;
			}
		}
		for (Cluster cl : copiedClusters) {
			rootCluster.addSubCluster(cl, iteration);
		}

	}

	@Override
	protected int getNeighborsNumber() {
		return 0;
	}

}