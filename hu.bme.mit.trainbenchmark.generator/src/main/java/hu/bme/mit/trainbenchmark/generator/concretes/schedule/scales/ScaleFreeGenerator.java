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
		initStations = 50;
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
		int index = 0;

		while (true) {
			index = RandomElementsProvider.getRandomIndex(random, stations);
			degree = ((Node) stations.get(index)).degree;
			if (degree == 0 && valid(connection, index)) {
				indices.add(index);
				addStationConnection(connection, sourceIndex, index);
			} else {
				percent = getPercent();
				if (degree >= percent) {
					if (!indices.contains(index)) {
						indices.add(index);
						addStationConnection(connection, sourceIndex, index);
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
			increaseDegree(STATION, source);
			increaseDegree(STATION, target);
			stations.get(source).conn.add(target);
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
					stations.get(lastConnIndex).conn.add(target);
				}
			}
			schedules.get(source).conn.add(target);
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

	protected boolean valid(final String connection, final int index) {
		if (connection == NEIGHBORS) {
			if (stations.size() - 1 == index) {
				return false;
			}
		}
		return true;
	}

	protected int getPercent() {
		if (maxDegree <= 2) {
			return random.nextInt(stationDegrees);
		} else {
			return random.nextInt(maxDegree);
		}
	}

	protected int getDestinationsNumber() {
		int percent = random.nextInt(1000);
		if (percent < 2 && stations.size() > 150) {
			// generate between 100-150
			return random.nextInt(51) + 100;
		} else if (percent < 42 && stations.size() > 100) {
			// generate between 50-99
			return random.nextInt(50) + 50;
		} else { // generate between 2-49
			percent = random.nextInt(100);
			if (percent < 42) {
				// generate between 2-11
				return random.nextInt(10) + 2;
			} else {
				percent = random.nextInt(100);
				if (percent < 4) {
					// 41 - 49
					return random.nextInt(9) + 41;
				} else if (percent < 14) {
					// 31 - 40
					return random.nextInt(10) + 31;
				} else if (percent < 42) {
					// 21 - 30
					return random.nextInt(10) + 21;
				} else {
					// 11 - 20
					return random.nextInt(10) + 11;
				}
			}

		}
	}

}
