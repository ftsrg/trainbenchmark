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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.ASSOCIATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.CATEGORY;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.DAYS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.DESTINATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.LOCATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.NEIGHBORS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.ORIGIN;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.PERMANENT;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.PLANNING;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULES;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SHORTTERM;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATUS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.TERMINAL;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.TRAIN;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.ScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScaleFreeGenerator extends ScheduleGenerator {

	protected int sizeStep;

	protected int initStations;

	protected int maxNumberOfStations;

	protected int maxNumberOfTrains;

	protected int associationPercent;

	protected int stationDegrees;

	protected int maxDegree;

	protected ArrayList<Node> stations;

	protected ArrayList<Node> schedules;

	public ScaleFreeGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void initializeConstants() {
		sizeStep = 5000;
		maxNodes = (int) (sizeStep * Math.pow(2, generatorConfig.getSize() - 1));
		maxNumberOfStations = (int) (maxNodes * 0.018);
		maxNumberOfTrains = (int) (maxNodes * 0.3566);
		associationPercent = 2;
		initStations = 50;
		stationDegrees = 0;
		maxDegree = -1;
		stations = new ArrayList<Node>();
		schedules = new ArrayList<Node>();

	}

	@Override
	protected void generateModel() throws IOException {
		System.out.print("Generate scale-free model...");
		initializationStep();

		nodes += maxNumberOfTrains;
		// generate station and schedule nodes plus the connections between them
		while (nodes < maxNodes) {
			if (stations.size() < maxNumberOfStations) {
				addStation();
				newStationConnections(NEIGHBORS, lastSt(), getNeighborsNumber());
			}
			addSchedule();
			newStationConnections(DESTINATIONS, lastSch(), getDestinationsNumber());
		}
		transformStationConnections();
		attachTrains();
		stations.clear();
		schedules.clear();
		System.out.println("Done!");
	}

	private void addSchedule() throws IOException {
		schedules.add(new Node(fg.createVertex(SCHEDULE, getScheduleAttributes()), 0));
		nodes++;
	}

	private void addStation() throws IOException {
		stations.add(new Node(fg.createVertex(STATION), 0));
		nodes++;
	}

	private void newStationConnections(final String connection, final int sourceIndex, final int amount)
			throws IOException {
		final ArrayList<Integer> indices = new ArrayList<>();
		int percent = 0;
		int degree = 0;
		int index = 0;

		while (true) {
			index = getRandomIndex(stations);
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

	private void transformStationConnections() throws IOException {
		for (Node node : stations) {
			for (Integer index : node.conn) {
				fg.createEdge(NEIGHBORS, node.obj, stations.get(index).obj);
			}
		}
		for (Node node : schedules) {
			if (node.conn.size() > 1) {
				fg.createEdge(ORIGIN, node.obj, stations.get(node.conn.get(0)).obj);
				for (int i = 1; i < node.conn.size() - 1; i++) {
					fg.createEdge(DESTINATIONS, node.obj,
							stations.get(node.conn.get(i)).obj);
				}
				// make a terminal connection to the last element of connected stations (conn)
				fg.createEdge(TERMINAL, node.obj, stations.get(node.lastConn()).obj);
			} else if (node.conn.size() == 1) {
				fg.createEdge(DESTINATIONS, node.obj, stations.get(node.conn.get(0)).obj);
			}
		}
	}

	private void attachTrains() throws IOException {
		int numOfTrains = 1;
		Map<String, Object> incoming = new HashMap<>();
		Map<String, Object> outgoing = new HashMap<>();
		ArrayList<Object> trains = new ArrayList<>();
		Object associative;

		for (Node node : schedules) {
			if (numOfTrains > maxNumberOfTrains) {
				fg.createEdge(SCHEDULES, getRandomElement(trains), node.obj);

				if (random.nextInt(100) <= associationPercent) {
					outgoing.clear();
					incoming.clear();
					// choose a distinct train randomly
					while (true) {
						associative = getRandomElement(trains);
						if (trains.get(trains.size() - 1) != associative) {
							break;
						}
					}
					outgoing.put(LOCATION, ((Node) getRandomElement(stations)).obj);
					outgoing.put(ScheduleModelConstants.ASSOCIATIVE, associative);
					incoming.put(ScheduleModelConstants.ASSOCIATIONS,
							trains.get(trains.size() - 1));
					fg.createVertex(ASSOCIATION, getAssociationAttributes(), outgoing,
							incoming);
				}

			} else {
				outgoing.clear();
				outgoing.put(SCHEDULES, node.obj);
				trains.add(fg.createVertex(TRAIN, emptyMap, outgoing));

				numOfTrains++;
			}
		}

	}

	private void addStationConnection(final String connection, final int source, final int target)
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
				// if the last connected station to the schedule does not belong to the target station, then
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

	private int getNeighborsNumber() {
		return random.nextInt(2) + 1;
	}

	private int getDestinationsNumber() {
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

	private int getPercent() {
		if (maxDegree <= 2) {
			return random.nextInt(stationDegrees);
		} else {
			return random.nextInt(maxDegree);
		}
	}

	private void initializationStep() throws IOException {
		for (int i = 0; i < initStations; i++) {
			addStation();
		}
	}

	private boolean valid(final String connection, final int index) {
		if (connection == NEIGHBORS) {
			if (lastSt() == index) {
				return false;
			}
		}
		return true;
	}

	private void increaseDegree(final String type, final Integer index) {
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

	private Map<String, Object> getAssociationAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		int percent = random.nextInt(100);
		if (percent < 8) {
			attributes.put(CATEGORY, "Divide");
		} else if (percent < 32) {
			attributes.put(CATEGORY, "Join");
		} else {
			attributes.put(CATEGORY, "Next");
		}

		percent = random.nextInt(100);
		if (percent < 1) {
			attributes.put(DAYS, "0100000");
		} else if (percent < 3) {
			attributes.put(DAYS, "0000100");
		} else if (percent < 22) {
			attributes.put(DAYS, "0000001");
		} else if (percent < 33) {
			attributes.put(DAYS, "1000000");
		} else if (percent < 66) {
			attributes.put(DAYS, "0000010");
		} else if (percent < 94) {
			attributes.put(DAYS, "1111100");
		} else {
			attributes.put(DAYS, getRandomBinary());
		}
		return attributes;
	}

	private String getRandomBinary() {
		int value = random.nextInt(128);
		String binary = Integer.toBinaryString(value);
		if (binary.length() < 7) {
			int length = binary.length();
			for (int i = 0; i < 7 - length; i++) {
				binary = "0".concat(binary);
			}
		}
		return binary;
	}

	private Map<String, Object> getScheduleAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		int percent = random.nextInt(1000);

		if (percent < 1) {
			attributes.put(STATUS, "Ship");
			attributes.put(PLANNING, PERMANENT);
		} else if (percent < 5) {
			attributes.put(STATUS, "Trip");
			addPlanning(attributes, 20);
		} else if (percent < 25) {
			attributes.put(STATUS, "Bus");
			addPlanning(attributes, 80);
		} else if (percent < 75) {
			attributes.put(STATUS, "Freight");
			addPlanning(attributes, 17);
		} else {
			attributes.put(STATUS, "Passenger");
			addPlanning(attributes, 13);
		}

		percent = random.nextInt(100);
		if (percent < 1) {
			attributes.put(DAYS, "0010000");
		} else if (percent < 2) {
			attributes.put(DAYS, "0001000");
		} else if (percent < 4) {
			attributes.put(DAYS, "0000100");
		} else if (percent < 6) {
			attributes.put(DAYS, "1111000");
		} else if (percent < 7) {
			attributes.put(DAYS, "1111110");
		} else if (percent < 8) {
			attributes.put(DAYS, "0100000");
		} else if (percent < 10) {
			attributes.put(DAYS, "0111100");
		} else if (percent < 20) {
			attributes.put(DAYS, "1000000");
		} else if (percent < 36) {
			attributes.put(DAYS, "1111100");
		} else if (percent < 65) {
			attributes.put(DAYS, "0000010");
		} else if (percent < 98) {
			attributes.put(DAYS, "0000001");
		} else {
			attributes.put(DAYS, getRandomBinary());
		}

		return attributes;
	}

	private void addPlanning(Map<String, Object> attributes, final int percent) {
		int v = random.nextInt(100);
		if (v < percent) {
			attributes.put(PLANNING, SHORTTERM);
		} else {
			attributes.put(PLANNING, PERMANENT);
		}
	}

	private int lastSch() {
		return schedules.size() - 1;
	}

	private int lastSt() {
		return stations.size() - 1;
	}

}
