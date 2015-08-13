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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.ASSOCIATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.DESTINATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.LOCATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.NEIGHBORS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.ORIGIN;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.SCHEDULES;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TERMINAL;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TRAIN;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.ScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScaleFreeGenerator extends ScheduleGenerator {

	protected int sizeStep;

	/**
	 * The number of initially created isolated nodes. It must be higher than m.
	 */
	protected int initStations;

	protected int maxNumberOfStations;

	protected int maxNumberOfTrains;

	protected int associationPercent;

	/**
	 * The number of edges that will be drawn for every new attached node.
	 */
	protected int m;

	private int stationDegrees;

	private int scheduleDegrees;

	private ArrayList<Node> stations;

	private ArrayList<Node> schedules;

	public ScaleFreeGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void initializeConstants() {
		sizeStep = 40;
		maxNodes = generatorConfig.getSize() * sizeStep;
		m = 5;
		maxNumberOfStations = 20;
		maxNumberOfTrains = 10;
		associationPercent = 20;
		initStations = 5;
		stationDegrees = 0;
		scheduleDegrees = 0;
		stations = new ArrayList<Node>();
		schedules = new ArrayList<Node>();

	}

	@Override
	protected void generateModel() throws IOException {
		System.out.print("Generate scale-free model...");
		initializationStep();

		// generate station and schedule nodes plus the connections between them
		while (nodes < maxNodes) {
			if (stations.size() < maxNumberOfStations) {
				addStation();
				newStationConnections(NEIGHBORS, lastSt(), m);
			}
			addSchedule();
			newStationConnections(DESTINATIONS, lastSch(), m);
		}

		transformStationConnections();
		attachTrains();
		System.out.println("Done!");
	}

	private void addSchedule() throws IOException {
		schedules.add(new Node(fg.createVertex(SCHEDULE), 0));
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

		while (true) {
			for (Integer index : getRandomIndices(stations, stations.size() / 2)) {
				degree = stations.get(index).degree;
				// connect automatically to isolated nodes
				if (degree == 0 && valid(connection, index)) {
					indices.add(index);
					addStationConnection(connection, sourceIndex, index);
				} else {
					percent = getPercent(connection);
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
					fg.createEdge(DESTINATIONS, node.obj, stations.get(node.conn.get(i)).obj);
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
					outgoing.put(ScheduleConstants.ASSOCIATIVE, associative);
					incoming.put(ScheduleConstants.ASSOCIATIONS, trains.get(trains.size() - 1));
					fg.createVertex(ASSOCIATION, emptyMap, outgoing, incoming);
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
				if (lastConnIndex != target && !stations.get(lastConnIndex).conn.contains(target)) {
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

	private int getPercent(final String connection) {
		switch (connection) {
		case NEIGHBORS:
			return random.nextInt(stationDegrees);
		case DESTINATIONS:
			return random.nextInt(stationDegrees + scheduleDegrees);
		default:
			throw new IllegalStateException("Illegal type of connection!");
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
			break;
		case SCHEDULE:
			schedules.get(index).degree++;
			scheduleDegrees++;
			break;
		default:
			throw new IllegalStateException("Illegal type of node!");
		}
	}

	private int lastSch() {
		return schedules.size() - 1;
	}

	private int lastSt() {
		return stations.size() - 1;
	}

}
