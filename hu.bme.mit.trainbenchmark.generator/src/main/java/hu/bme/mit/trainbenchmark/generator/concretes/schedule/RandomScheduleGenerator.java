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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.util.Node;
import hu.bme.mit.trainbenchmark.generator.util.RandomElementsProvider;

import java.io.IOException;
import java.util.ArrayList;

public class RandomScheduleGenerator extends HomogeneousScheduleGenerator {

	protected double p;
	protected int numberOfRandomStations;
	protected int numberOfEdges;

	public RandomScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate random model " + submodel + ", size: " + generatorConfig.getSize()
				+ "...");

	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		double edges = getEstimatedNumberOfNeighbors();
		p = edges / binomial(maxNumberOfStations, 2);
		p /= 4;

	}

	@Override
	protected void initializationStep() throws IOException {
	}

	@Override
	protected void generateStations() throws IOException {
		while (currentNodes < maxNumberOfStations) {
			currentNodes++;
			stations.add(new Node(0, currentNodes));
		}

		for (int i = 0; i < stations.size(); i++) {
			newNeighbors(i);
		}
		if (!checkConnectivity()) {
			ArrayList<Node> isolated = new ArrayList<Node>(stations);
			isolated.removeAll(checked);
			int targetIndex;
			int i = 0;
			while (i < isolated.size()) {
				targetIndex = RandomElementsProvider.getRandomDisjunctIndex(random, stations,
						isolated.get(i).id);
				if (!isolated.contains(stations.get(targetIndex))) {
					addNeighbor(stations.indexOf(isolated.get(i)), targetIndex);
					addNeighbor(targetIndex, stations.indexOf(isolated.get(i)));
					i++;
				}
			}
		}
		for (Node node : stations) {
			node.obj = fg.createVertex(STATION);
		}
	}

	@Override
	protected int getNeighborsNumber() {
		return 2;
	}

	protected void newNeighbors(final int sourceIndex) {
		for (int i = 0; i < stations.size(); i++) {
			if (sourceIndex != i) {
				if (random.nextDouble() < p) {
					addNeighbor(sourceIndex, i);
					addNeighbor(i, sourceIndex);
				}
			}
		}
	}

	protected long binomial(int n, int k) {
		if (k > n - k)
			k = n - k;

		long b = 1;
		for (int i = 1, m = n; i <= k; i++, m--)
			b = b * m / i;
		return b;
	}

}
