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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.DESTINATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.NEIGHBORS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.util.Node;
import hu.bme.mit.trainbenchmark.generator.util.RandomElementsProvider;

import java.io.IOException;
import java.util.ArrayList;

public class ScaleFreeGenerator extends Generator {

	protected ArrayList<Node> stations;

	protected ArrayList<Node> schedules;

	protected int initStations;

	protected int stationDegrees;

	protected int maxDegree;

	protected ScaleFreeModel model;

	public ScaleFreeGenerator(ScaleFreeModel model) {
		this.stations = model.getStations();
		this.schedules = model.getSchedules();
		this.model = model;
	}

	public void initializeConstants() {
		if (model.getMaxNewNeighbors() > 5) {
			initStations = model.getMaxNewNeighbors();
		} else {
			initStations = 5;
		}
		stationDegrees = 0;
		maxDegree = -1;

	}

	public void initializationStep() throws IOException {
		for (int i = 0; i < initStations; i++) {
			model.addNewStation();
		}
	}

	public void newStationConnections(final String connection, final int sourceIndex, final int amount)
			throws IOException {
		if (amount == 0) {
			return;
		}
		final ArrayList<Integer> indices = new ArrayList<>();
		int percent = 0;
		int degree = 0;
		int targetIndex = 0;

		while (true) {
			targetIndex = RandomElementsProvider.getRandomIndex(random, stations);
			degree = ((Node) stations.get(targetIndex)).degree;
			if (sourceIndex != targetIndex) {
				if (degree == 0) {
					indices.add(targetIndex);
					addStationConnection(connection, sourceIndex, targetIndex);
				} else {
					percent = getPercent();
					if (degree >= percent) {
						if (!indices.contains(targetIndex)) {
							indices.add(targetIndex);
							addStationConnection(connection, sourceIndex,
									targetIndex);
						}
					}
				}
			}
			if (indices.size() == amount) {
				return;
			}
		}
	}

	protected void addStationConnection(final String connection, final int source, final int target)
			throws IOException {
		switch (connection) {
		case NEIGHBORS:
			// add twice
			increaseDegree(STATION, source);
			increaseDegree(STATION, target);
			model.addNewNeighbor(source, target);

			increaseDegree(STATION, source);
			increaseDegree(STATION, target);
			model.addNewNeighbor(target, source);
			break;
		case DESTINATIONS:
			increaseDegree(SCHEDULE, source);
			increaseDegree(STATION, target);
			if (schedules.get(source).conn.size() > 0) {
				// if the last station connected to the schedule does not belong to the target station, then
				// create a new neighbor connection
				int lastConnIndex = schedules.get(source).lastConn();
				if (lastConnIndex != target
						&& !stations.get(lastConnIndex).conn.contains(target)) {
					increaseDegree(STATION, lastConnIndex);
					increaseDegree(STATION, target);
					model.addNewNeighbor(lastConnIndex, target);
				}
			}
			model.addNewDestination(source, target);
			break;
		default:
			throw new IllegalStateException("Illegal type of connection!");
		}
	}

	protected void increaseDegree(final String type, final Integer index) {
		switch (type) {
		case STATION:
			stations.get(index).degree++;
			stationDegrees++;
			if (maxDegree == -1) { // search maximum degree and its index
				maxDegree = stations.get(0).degree;
				for (int i = 1; i < stations.size(); i++) {
					if (stations.get(i).degree > maxDegree) {
						maxDegree = stations.get(i).degree;
					}
				}
			} else if (stations.get(index).degree > maxDegree) {
				maxDegree = stations.get(index).degree;
			}
			break;
		case SCHEDULE:
			schedules.get(index).degree++;
			break;
		default:
			throw new IllegalStateException("Illegal type of node!");
		}
	}

	protected int getPercent() {
		if (maxDegree <= 2) {
			return random.nextInt(stationDegrees);
		} else {
			return random.nextInt(maxDegree);
		}
	}

}
