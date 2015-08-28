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

public class RandomScheduleGenerator extends HomogeneousScheduleGenerator {

	protected double p;

	protected int numberOfRandomStations;

	public RandomScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate random model...");

	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		p = 0.002;
		for (int i = 4; i > 0; i--) {
			int value = (int) Math.pow(10, i);
			if (p * value < 1.0) {
				numberOfRandomStations = value;
				break;
			}
		}

	}

	@Override
	protected void initializationStep() throws IOException {
		while (currentNodes < maxNumberOfStations) {
			addStation();
		}
		if (numberOfRandomStations > stations.size()) {
			numberOfRandomStations = stations.size() / 2;
		}
	}

	@Override
	protected void generateStations() throws IOException {
		for (int i = 0; i < stations.size(); i++) {
			newNeighbors(i);
		}
	}

	@Override
	protected int getNeighborsNumber() {
		return 2;
	}

	protected void newNeighbors(final int sourceIndex) {
		int i = 0;
		while (i < numberOfRandomStations) {
			int stationIndex = RandomElementsProvider.getRandomDisjunctIndex(random, stations,
					sourceIndex);
			if (!connected(sourceIndex, stationIndex)) {
				if (random.nextDouble() < p * numberOfRandomStations) {
					stations.get(sourceIndex).conn.add(stationIndex);
				}
				// increment anyway
				i++;
			}
		}
	}

	protected boolean connected(final int sourceIndex, final int targetIndex) {
		if (stations.get(sourceIndex).conn.contains(targetIndex)) {
			return true;
		}
		if (stations.get(targetIndex).conn.contains(sourceIndex)) {
			return true;
		}
		return false;
	}

}
