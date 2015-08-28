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

package hu.bme.mit.trainbenchmark.generator.concretes.schedule.scales;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.NEIGHBORS;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.ScaleFreeGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomogeneousScaleFreeGenerator extends ScaleFreeGenerator {

	public HomogeneousScaleFreeGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate homogeneous scale-free model...");
	}

	@Override
	protected int getNeighborsNumber() {
		return 2;
	}

	@Override
	protected int getDestinationsNumber() {
		double y = random.nextDouble();
		double n = -3.0;
		double x0 = 2.0;
		double x1 = 150.0;
		double x = Math.pow((Math.pow(x1, n + 1) - Math.pow(x0, n + 1)) * y + Math.pow(x0, n + 1),
				1 / (n + 1));
		return (int) x;
	}

	@Override
	protected void generateSchedules() throws IOException {
		if (nodes >= maxNumberOfStations) {
			super.generateSchedules();
		}
	}

	@Override
	protected void newStationConnections(final String connection, final int sourceIndex, final int amount)
			throws IOException {
		if (connection.equals(NEIGHBORS)) {
			super.newStationConnections(connection, sourceIndex, amount);
			return;
		}
		if (amount == 0) {
			return;
		}
		int tries = 0;
		int maxTries = 10;
		int stationIndex;
		List<Integer> stationIndices;
		while (tries < maxTries) {
			stationIndex = getRandomIndex(stations);
			// choose a random station that has neighbors for sure
			if (stations.get(stationIndex).conn.size() == 0) {
				continue;
			}
			stationIndices = findPath(connection, sourceIndex, amount, stationIndex);
			if (stationIndices.size() != amount) {
				if (tries == maxTries - 1) {
					for (Integer index : stationIndices) {
						addStationConnection(connection, sourceIndex, index);
					}
					return;
				}
				tries++;
			} else {
				for (Integer index : stationIndices) {
					addStationConnection(connection, sourceIndex, index);
				}
				return;
			}

		}

	}

	protected List<Integer> findPath(final String connection, final int sourceIndex, final int amount,
			final int stationIndex) {
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
			index = (int) getRandomElement(station.conn);
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
