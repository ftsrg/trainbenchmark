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
import hu.bme.mit.trainbenchmark.generator.util.Node;
import hu.bme.mit.trainbenchmark.generator.util.RandomElementsProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class HomogeneousScheduleGenerator extends ScheduleGenerator {

	public HomogeneousScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void generateSchedules() throws IOException {
		while (currentNodes < maxNumberOfSchedules) {
			addSchedule();
			int numberOfDestinations = getDestinationsNumber();
			if (currentNodes < maxNumberOfSchedules - maxNumberOfRepetitiveSchedules) {
				if (!schedulesOfDestinations.containsKey(numberOfDestinations)) {
					schedulesOfDestinations.put(numberOfDestinations,
							new ArrayList<Integer>());
				}
				schedulesOfDestinations.get(numberOfDestinations).add(lastSch());
				attachDestinations(lastSch(), numberOfDestinations);
			} else {
				addRepetitiveScheduleConnections(lastSch(), numberOfDestinations);
			}
		}
	}

	protected void attachDestinations(final int sourceIndex, final int amount) throws IOException {
		if (amount == 0) {
			return;
		}
		int tries = 0;
		int maxTries = 10;
		int stationIndex;
		List<Integer> stationIndices;
		while (tries < maxTries) {
			stationIndex = RandomElementsProvider.getRandomIndex(random, stations);
			// choose a random station that has neighbors for sure
			if (stations.get(stationIndex).conn.size() == 0) {
				continue;
			}
			stationIndices = findPath(sourceIndex, amount, stationIndex);
			if (stationIndices.size() != amount) {
				if (tries == maxTries - 1) {
					for (Integer index : stationIndices) {
						addDestination(sourceIndex, index);
					}
					return;
				}
				tries++;
			} else {
				for (Integer index : stationIndices) {
					addDestination(sourceIndex, index);
				}
				return;
			}

		}
	}

	protected List<Integer> findPath(final int sourceIndex, final int amount, final int stationIndex) {
		List<Integer> stationIndices = new ArrayList<Integer>();
		// first station in the path
		Node station = stations.get(stationIndex);
		int pathSize = 1; // station is the first node in the path, thus, the path starts from 1
		int index = stationIndex;
		int tries = 0; // avoid infinite loop
		int maxTries = 10;
		while (pathSize <= amount && tries < maxTries) {
			// if there are no further neighbors
			if (station.conn.size() == 0) {
				return stationIndices;
			}
			index = (int) RandomElementsProvider.getRandomElement(random, station.conn);
			if (stationIndices.contains(index)) {
				tries++;
				continue;
			}
			station = stations.get(index);
			stationIndices.add(index);
			pathSize++;
		}
		return stationIndices;
	}

}
