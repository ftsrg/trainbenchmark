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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Integer> bestTriedStations = null;
		List<Integer> stationIndices;
		while (tries < maxTries) {
			stationIndex = RandomElementsProvider.getRandomIndex(random, stations);
			// choose a random station that has neighbors for sure
			if (stations.get(stationIndex).conn.size() == 0) {
				continue;
			}
			stationIndices = findPath(stationIndex, amount);
			if (stationIndices.size() != amount) {
				if (tries == maxTries - 1) {
					if (bestTriedStations.size() >= stationIndices.size()) {
						for (Integer index : bestTriedStations) {
							addDestination(sourceIndex, index);
						}
						return;
					} else if (stationIndices.size() < bestTriedStations.size()) {
						for (Integer index : stationIndices) {
							addDestination(sourceIndex, index);
						}
						return;

					}
				}
				if (bestTriedStations == null) {
					bestTriedStations = stationIndices;
				} else {
					if (bestTriedStations.size() < stationIndices.size()) {
						bestTriedStations = stationIndices;
					}
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

	protected List<Integer> findPath(final int sourceIndex, final int maxDepth) {
		List<Integer> queue = new ArrayList<Integer>();
		queue.add(sourceIndex);

		int depth = 1;
		Map<Integer, Map<String, Integer>> checked = new HashMap<Integer, Map<String, Integer>>();
		checked.put(sourceIndex, new HashMap<String, Integer>());
		checked.get(sourceIndex).put("Depth", depth);
		checked.get(sourceIndex).put("Prev", -1); // non-existing previous index
		List<Integer> path = new ArrayList<Integer>();
		int currentIndex = -1; // dummy init
		while (!queue.isEmpty()) {
			currentIndex = queue.get(0);
			queue.remove(0);
			depth = checked.get(currentIndex).get("Depth");
			if (depth == maxDepth) {
				break;
			}
			for (Integer neighbor : stations.get(currentIndex).conn) {
				// exclude loop
				if (neighbor != currentIndex) {
					if (!checked.containsKey(neighbor)) {
						checked.put(neighbor, new HashMap<String, Integer>());
					}
					if (checked.containsKey(neighbor)) {
						if (!validNeighbor(checked, currentIndex, neighbor)) {
							continue;
						}

					}
					queue.add(0, neighbor);
					// override previously checked nodes
					checked.get(neighbor).put("Depth", depth + 1);
					checked.get(neighbor).put("Prev", currentIndex);

				}
			}

		}

		path.add(currentIndex);
		int i = 0;
		while (i <= maxDepth) {
			currentIndex = checked.get(currentIndex).get("Prev");
			if (currentIndex == -1) {
				break;
			}
			path.add(currentIndex);
			i++;
		}

		return path;
	}

	protected boolean validNeighbor(final Map<Integer, Map<String, Integer>> checked,
			final int currentIndex, final Integer neighbor) {
		int prevIndex;
		int current = currentIndex;
		while (true) {
			prevIndex = checked.get(current).get("Prev");

			if (prevIndex == neighbor) {
				return false;
			}
			if (prevIndex == -1) {
				return true;
			}
			current = prevIndex;
		}
	}

	protected List<Integer> findPath2(final int sourceIndex, final int amount, final int stationIndex) {
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
