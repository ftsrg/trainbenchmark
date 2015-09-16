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
import hu.bme.mit.trainbenchmark.generator.util.RandomElementsProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WattsStrogatzScheduleGenerator extends HomogeneousScheduleGenerator {

	protected double p;
	protected int K;
	protected int minDegree;
	protected int maxDegree;
	protected double minDegreePercent;

	public WattsStrogatzScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate Watts-Strogatz model " + submodel + ", size: "
				+ generatorConfig.getSize() + "...");

	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		double edges = getEstimatedNumberOfNeighbors();
		double averageDegree = edges / maxNumberOfStations;
		minDegree = (int) averageDegree;
		maxDegree = (int) averageDegree + 1;
		minDegreePercent = 1 - (averageDegree - minDegree);
		p = 0.01;
	}

	@Override
	protected void initializationStep() throws IOException {
		while (currentNodes < maxNumberOfStations) {
			addStation();
		}

	}

	@Override
	protected void generateStations() throws IOException {
		int backward = 0;
		int forward = 0;
		for (int source = 0; source < stations.size(); source++) {
			int neighbors = getNeighborsNumber();
			if (neighbors % 2 == 0) {
				backward = neighbors / 2;
				forward = neighbors / 2;
			} else {
				backward = neighbors / 2;
				forward = neighbors / 2 + 1;
			}
			for (int offset = 1; offset <= forward; offset++) {
				addNeighborWithOffset(source, offset);
			}
			for (int offset = 1; offset <= backward; offset++) {
				addNeighborWithOffset(source, offset * -1);
			}
		}
		rewireNeighbors();

	}

	protected boolean addNeighborWithOffset(final int sourceIndex, final int offset) {
		int target = sourceIndex + offset;
		if (target >= stations.size()) {
			// jump to the beginning
			target = offset - (stations.size() - sourceIndex);
		} else if (target < 0) {
			// jump to the ending, in this case offset must be negative
			target = stations.size() + offset;
		}
		return super.addNeighbor(sourceIndex, target);
	}

	protected void rewireNeighbors() {
		List<Integer> removedNeighbors = new ArrayList<Integer>();
		List<Integer> addedNeighbors = new ArrayList<Integer>();
		for (int i = 0; i < stations.size(); i++) {
			removedNeighbors.clear();
			addedNeighbors.clear();
			for (Integer targetIndex : stations.get(i).conn) {
				if (random.nextDouble() < p) {
					int newTargetIndex = i;
					while (i == newTargetIndex) {
						newTargetIndex = RandomElementsProvider
								.getRandomDisjunctIndex(random, stations,
										targetIndex);
					}
					addedNeighbors.add(newTargetIndex);
					removedNeighbors.add(targetIndex);
				}
			}
			for (Integer target : removedNeighbors) {
				removeNeighbor(i, target);
				removeNeighbor(target, i);
			}
			for (Integer target : addedNeighbors) {
				addNeighbor(i, target);
				addNeighbor(target, i);
			}
		}
	}

	@Override
	protected int getNeighborsNumber() {
		if (random.nextDouble() < minDegreePercent) {
			return minDegree;
		} else {
			return maxDegree;
		}
	}

}
