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
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.END_DATE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.INDICATOR_C;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.INDICATOR_N;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.INDICATOR_O;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.INDICATOR_P;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.LOCATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.NEIGHBORS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.ORIGIN;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.PERMANENT;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.PLANNING;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULES;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SHORTTERM;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.START_DATE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATUS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STP_INDICATOR;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.TERMINAL;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.TRAIN;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleGeneratorConstants;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleSubmodels;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.SyntheticGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.util.Node;
import hu.bme.mit.trainbenchmark.generator.util.RandomElementsProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public abstract class ScheduleGenerator extends SyntheticGenerator {

	protected int maxNodes;
	protected int currentNodes;
	protected int sizeStep;
	protected int maxNumberOfStations;
	protected int maxNumberOfTrains;
	protected int maxNumberOfSchedules;
	protected int maxNumberOfRepetitiveSchedules;
	protected int associationPercent;
	protected double repetitiveScheduleFactor;

	protected Random deterministicRandom;

	protected ArrayList<Node> stations;
	protected ArrayList<Node> schedules;

	protected Map<Integer, List<Integer>> schedulesOfDestinations;

	protected ScheduleSubmodels submodel;

	public ScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	protected abstract void printMessage();

	protected abstract void initializationStep() throws IOException;

	protected abstract void generateSchedules() throws IOException;

	protected abstract void generateStations() throws IOException;

	protected abstract int getNeighborsNumber();

	@Override
	protected void initializeConstants() {
		sizeStep = ScheduleGeneratorConstants.sizeStep;
		maxNodes = (int) (sizeStep * Math.pow(2, generatorConfig.getSize() - 1));
		maxNumberOfStations = (int) (maxNodes * ScheduleGeneratorConstants.stationsProportion);
		maxNumberOfTrains = (int) (maxNodes * ScheduleGeneratorConstants.trainsProportion);
		maxNumberOfSchedules = maxNodes - maxNumberOfStations - maxNumberOfTrains;
		associationPercent = 2;
		repetitiveScheduleFactor = ScheduleGeneratorConstants.repetitiveScheduleFactor;
		maxNumberOfRepetitiveSchedules = (int) (maxNumberOfSchedules * repetitiveScheduleFactor);
		stations = new ArrayList<Node>();
		schedules = new ArrayList<Node>();
		schedulesOfDestinations = new HashMap<Integer, List<Integer>>();
		submodel = generatorConfig.getSubmodel();

		deterministicRandom = new Random(TrainBenchmarkConstants.RANDOM_SEED);
	}

	@Override
	protected void generateModel() throws IOException {
		printMessage();

		initializationStep();
		generateStations();
		generateSchedules();
		transformStationConnections();
		attachTrains();

		checkConnectivity();
		stations.clear();
		schedules.clear();
		System.out.println("Done!");
	}

	protected void addSchedule() throws IOException {
		schedules.add(new Node(fg.createVertex(SCHEDULE, getScheduleAttributes()), 0));
		currentNodes++;
	}

	protected void addStation() throws IOException {
		stations.add(new Node(fg.createVertex(STATION), 0));
		currentNodes++;
	}

	protected void addRepetitiveScheduleConnections(final int sourceIndex, int destinations) {
		while (true) {
			if (!schedulesOfDestinations.containsKey(destinations)) {
				destinations--;
				if (destinations < 2) {
					destinations = deterministicRandom.nextInt(schedulesOfDestinations
							.size()) + 2;
				}
			} else {
				List<Integer> scheduleIndices = schedulesOfDestinations.get(destinations);
				int index = deterministicRandom.nextInt(scheduleIndices.size());
				for (Integer c : schedules.get(index).conn) {
					addDestination(sourceIndex, c);
				}
				return;
			}

		}
	}

	protected boolean addNeighbor(final int sourceIndex, final int targetIndex) {
		if (stations.get(sourceIndex).conn.contains(targetIndex)) {
			return false;
		}
		return stations.get(sourceIndex).conn.add(targetIndex);
	}

	protected boolean removeNeighbor(final int sourceIndex, final int targetIndex) {
		// cast int to Integer, since we remove an Object from the ArrayList instead removing an index
		return (stations.get(sourceIndex).conn).remove((Integer) targetIndex);
	}

	protected boolean addDestination(final int sourceIndex, final int targetIndex) {
		if (schedules.get(sourceIndex).conn.contains(targetIndex)) {
			return false;
		}
		return schedules.get(sourceIndex).conn.add(targetIndex);
	}

	protected void transformStationConnections() throws IOException {
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

	protected int getDestinationsNumber() {
		double exponent = -3.0;
		double x0 = 2.0;
		double x1 = generatorConfig.getSize() * 9;
		if (x1 > stations.size()) {
			x1 = stations.size() / 2;
		}
		int x = 0;
		while (true) {
			double uniformRandom = deterministicRandom.nextDouble();
			x = (int) getPowerLawValue(uniformRandom, exponent, x0, x1);
			if (x <= x1) {
				break;
			}
		}
		return (int) x;
	}

	protected void checkConnectivity() {
		Set<Node> checked = new HashSet<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(stations.get(0));
		Node currentNode;
		Node neighborNode;
		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			for (Integer neighbor : currentNode.conn) {
				neighborNode = stations.get(neighbor);
				if (neighborNode != currentNode && !checked.contains(neighborNode)) {
					queue.add(neighborNode);
				}
				checked.add(neighborNode);
			}
			if (checked.size() == stations.size()) {
				return;
			}
		}
		if (checked.size() != stations.size()) {
			throw new RuntimeException();
		}
	}

	protected void attachTrains() throws IOException {
		int numOfTrains = 1;
		Map<String, Object> incoming = new HashMap<>();
		Map<String, Object> outgoing = new HashMap<>();
		ArrayList<Object> trains = new ArrayList<>();
		Object associativeTrain;
		Object mainTrain;

		for (Node node : schedules) {
			if (numOfTrains > maxNumberOfTrains) {
				fg.createEdge(SCHEDULES, RandomElementsProvider.getRandomElement(
						deterministicRandom, trains), node.obj);

				if (deterministicRandom.nextInt(100) <= associationPercent) {
					outgoing.clear();
					incoming.clear();
					// choose distinct trains randomly
					while (true) {
						associativeTrain = RandomElementsProvider.getRandomElement(
								deterministicRandom, trains);
						mainTrain = RandomElementsProvider.getRandomElement(
								deterministicRandom, trains);
						if (mainTrain != associativeTrain) {
							break;
						}
					}
					outgoing.put(LOCATION, ((Node) RandomElementsProvider
							.getRandomElement(deterministicRandom, stations)).obj);
					outgoing.put(ScheduleModelConstants.ASSOCIATIVE, associativeTrain);
					incoming.put(ScheduleModelConstants.ASSOCIATIONS, mainTrain);
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

	protected Map<String, Object> getAssociationAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		int percent = deterministicRandom.nextInt(100);
		if (percent < 8) {
			attributes.put(CATEGORY, "Divide");
		} else if (percent < 32) {
			attributes.put(CATEGORY, "Join");
		} else {
			attributes.put(CATEGORY, "Next");
		}

		addIndicator(attributes);

		addRandomDates(attributes);

		percent = deterministicRandom.nextInt(100);
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

	protected String getRandomBinary() {
		int value = deterministicRandom.nextInt(128);
		String binary = Integer.toBinaryString(value);
		if (binary.length() < 7) {
			int length = binary.length();
			for (int i = 0; i < 7 - length; i++) {
				binary = "0".concat(binary);
			}
		}
		return binary;
	}

	protected Map<String, Object> getScheduleAttributes() {
		Map<String, Object> attributes = new HashMap<>();
		int percent = deterministicRandom.nextInt(1000);

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

		addIndicator(attributes);
		addRandomDates(attributes);

		percent = deterministicRandom.nextInt(100);
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

	protected void addPlanning(final Map<String, Object> attributes, final int percent) {
		int planningPercent = deterministicRandom.nextInt(100);
		if (planningPercent < percent) {
			attributes.put(PLANNING, SHORTTERM);
		} else {
			attributes.put(PLANNING, PERMANENT);
		}
	}

	protected void addIndicator(final Map<String, Object> attributes) {
		int percent = deterministicRandom.nextInt(100);
		if (!attributes.containsKey(PLANNING)) {
			if (percent < 12) {
				attributes.put(STP_INDICATOR, INDICATOR_C);
			} else if (percent < 35) {
				attributes.put(STP_INDICATOR, INDICATOR_N);
			} else if (percent < 60) {
				attributes.put(STP_INDICATOR, INDICATOR_O);
			} else {
				attributes.put(STP_INDICATOR, INDICATOR_P);
			}
			return;
		}
		if (attributes.get(PLANNING) == PERMANENT) {
			if (percent < 23) {
				attributes.put(STP_INDICATOR, INDICATOR_C);
			} else {
				attributes.put(STP_INDICATOR, INDICATOR_P);
			}
		} else if (percent < 46) {
			attributes.put(STP_INDICATOR, INDICATOR_N);
		} else {
			attributes.put(STP_INDICATOR, INDICATOR_O);
		}

	}

	protected void addRandomDates(final Map<String, Object> attributes) {
		int startYear = deterministicRandom.nextInt(2) + 2014;
		int endYear = startYear;
		int startDay = deterministicRandom.nextInt(365) + 1;
		int endDay = deterministicRandom.nextInt(365);
		if (startDay > endDay) {
			endYear++;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, startYear);
		calendar.set(Calendar.DAY_OF_YEAR, startDay);
		attributes.put(START_DATE,
				createDate(startYear, calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH)));

		calendar.set(Calendar.YEAR, endYear);
		calendar.set(Calendar.DAY_OF_YEAR, endDay);
		attributes.put(END_DATE,
				createDate(endYear, calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH)));
	}

	protected String createDate(final int year, final int month, final int day) {
		String m = Integer.toString(month);
		String d = Integer.toString(day);
		if (m.length() == 1) {
			m = "0".concat(m);
		}
		if (d.length() == 1) {
			d = "0".concat(d);
		}
		return Integer.toString(year) + "-" + m + "-" + d;
	}

	protected int lastSch() {
		return schedules.size() - 1;
	}

	protected int lastSt() {
		return stations.size() - 1;
	}
}