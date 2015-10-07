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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HomogeneousScheduleGenerator extends ScheduleGenerator {

	public HomogeneousScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void generateSchedules() throws IOException {
		int numberOfSchedules = 0;
		while (numberOfSchedules < maxNumberOfSchedules) {
			addSchedule();
			numberOfSchedules++;
			int numberOfDestinations = getDestinationsNumber();
			if (numberOfSchedules < maxNumberOfSchedules - maxNumberOfRepetitiveSchedules) {
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
					System.out.println("");
					System.out.print("amount: " + amount);
					System.out.print("best: " + bestTriedStations.size());
					System.out.println("current: " + stationIndices.size());
					System.out.println(bestTriedStations);
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

		int depth = 0;
		Map<Integer, Vertex> checked = new HashMap<>();
		checked.put(sourceIndex, new Vertex());
		checked.get(sourceIndex).depth = depth;
		int currentIndex = -1; // dummy init
		while (!queue.isEmpty()) {
			currentIndex = queue.get(0);
			queue.remove(0);
			depth = checked.get(currentIndex).depth;
			if (depth == maxDepth) {
				break;
			}
			for (Integer neighborIndex : stations.get(currentIndex).conn) {

				if (neighborIndex != currentIndex) {
					if (!checked.containsKey(neighborIndex)) {
						checked.put(neighborIndex, new Vertex());
						checked.get(neighborIndex).depth = depth + 1;
						checked.get(neighborIndex).prev.addAll(checked
								.get(currentIndex).prev);
						checked.get(neighborIndex).prev.add(currentIndex);
						queue.add(neighborIndex);
						checked.get(currentIndex).next.add(neighborIndex);
					} else {
						if (!checked.get(currentIndex).prev.contains(neighborIndex)) {
							if (depth >= checked.get(neighborIndex).depth) {
								checked.get(neighborIndex).depth = depth + 1;
								checked.get(neighborIndex).prev.clear();
								checked.get(neighborIndex).prev
										.addAll(checked.get(currentIndex).prev);
								checked.get(neighborIndex).prev
										.add(currentIndex);
								revisitNeighbors(checked, neighborIndex);
								queue.add(neighborIndex);
							}
						}
					}
				}
			}
		}
		return checked.get(currentIndex).prev;
	}

	protected void revisitNeighbors(Map<Integer, Vertex> checked, int neighbor) {
		int depth = checked.get(neighbor).depth;
		if (!checked.get(neighbor).next.isEmpty()) {
			for (Integer nextIndex : checked.get(neighbor).next) {
				if (!checked.get(neighbor).prev.contains(nextIndex)) {
					checked.get(nextIndex).prev.clear();
					checked.get(nextIndex).prev.addAll(checked.get(neighbor).prev);
					checked.get(nextIndex).prev.add(neighbor);
					checked.get(nextIndex).depth = depth + 1;
				}
			}
			for (Integer nextIndex : checked.get(neighbor).next) {
				revisitNeighbors(checked, nextIndex);
			}

		}
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

	protected class Vertex {

		protected int depth;

		protected List<Integer> prev;
		protected List<Integer> next;

		protected Vertex() {
			prev = new ArrayList<Integer>();
			next = new ArrayList<Integer>();
		}
	}

}
