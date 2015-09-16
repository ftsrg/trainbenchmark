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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
		int maxTries = 50;
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
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(sourceIndex);

		int depth = 0;
		Map<Integer, Vertex> checked = new HashMap<>();
		checked.put(sourceIndex, new Vertex());
		checked.get(sourceIndex).depth = depth;
		int currentIndex = -1; // dummy init
		List<Integer> exploredRandomNeighbors = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			currentIndex = queue.poll();
			depth = checked.get(currentIndex).depth;
			if (depth == maxDepth) {
				break;
			}
			exploredRandomNeighbors.clear();
			for (Integer n : stations.get(currentIndex).conn) {
				int neighbor = (int) RandomElementsProvider.getRandomElement(random,
						stations.get(currentIndex).conn);
				while (exploredRandomNeighbors.contains(neighbor)
						&& exploredRandomNeighbors.size() < stations
								.get(currentIndex).conn.size()) {
					neighbor = (int) RandomElementsProvider.getRandomElement(random,
							stations.get(currentIndex).conn);
				}
				exploredRandomNeighbors.add(neighbor);
				if (neighbor != currentIndex) {
					if (!checked.containsKey(neighbor)) {
						checked.put(neighbor, new Vertex());
						checked.get(neighbor).depth = depth + 1;
						checked.get(neighbor).prev
								.addAll(checked.get(currentIndex).prev);
						checked.get(neighbor).prev.add(currentIndex);
						queue.add(neighbor);
						checked.get(currentIndex).next.add(neighbor);
					} else {
						if (!checked.get(currentIndex).prev.contains(neighbor)) {
							if (depth >= checked.get(neighbor).depth) {
								checked.get(neighbor).depth = depth + 1;
								checked.get(neighbor).prev.clear();
								checked.get(neighbor).prev.addAll(checked
										.get(currentIndex).prev);
								checked.get(neighbor).prev.add(currentIndex);
								revisitNeighbors(checked, neighbor);
								queue.add(neighbor);
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
