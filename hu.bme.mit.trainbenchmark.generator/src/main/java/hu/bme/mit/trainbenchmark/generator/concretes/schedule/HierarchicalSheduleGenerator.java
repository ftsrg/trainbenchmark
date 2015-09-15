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

import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleSubmodels;
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
	protected double maxIterationsDouble;
	protected int numberOfClones;

//	protected double expected;

	public HierarchicalSheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate hierarchical schedule model " + submodel + ", size: "
				+ generatorConfig.getSize() + "...");
	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		initClusters();
//		expected = estimateNumberOfNeighbors();
	}

	protected void initClusters() {
		clusterSize = calculateClusterSize(generatorConfig.getSubmodel());

		numberOfClones = 4;
		maxIterationsDouble = calculateIterations();
		maxIterations = (int) maxIterationsDouble;

		Cluster.numberOfNodes = clusterSize;
		Cluster.maxID = maxNumberOfStations;
	}

	protected int calculateClusterSize(final ScheduleSubmodels subModel) {
		switch (subModel) {
		case A:
			return 3;
		case B:
			return 5;
		case C:
			return 7;
		case D:
			return 9;
		case E:
			return 11;
		default:
			throw new IllegalArgumentException("Not supported submodel: " + subModel);
		}
	}

	protected double calculateIterations() {
		return (Math.log10(maxNumberOfStations / (double) clusterSize) / Math
				.log10(numberOfClones + 1)) + 1;
	}

	protected double estimateNumberOfNeighbors() {
		int k = maxIterations;
		double possibleStations = Math.pow(numberOfClones + 1, k) * clusterSize;
		double expected = calculateClusters(k) * 2 * (maxNumberOfStations / possibleStations);
		if (generatorConfig.getSize() <= 4) {
			if (maxIterationsDouble - maxIterations <= 0.31) {
				expected *= 0.92;
			} else if (maxIterationsDouble - maxIterations < 0.5) {
				expected *= 0.96;
			}
		} else if (generatorConfig.getSize() == 5) {
			if (maxIterationsDouble - maxIterations <= 0.31) {
				expected *= 0.96;
			}
		}
		return expected;
	}

	protected double calculateClusters(int k) {
		if (k == 0) {
			return (clusterSize * (clusterSize - 1)) / 2;
		}
		return (numberOfClones + 1) * calculateClusters(k - 1) + Math.pow(numberOfClones, k)
				* (clusterSize - 1);
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
		for (int i = 0; i < numberOfClones; i++) {
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
