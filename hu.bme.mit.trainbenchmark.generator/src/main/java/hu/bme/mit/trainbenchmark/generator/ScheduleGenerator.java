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

package hu.bme.mit.trainbenchmark.generator;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.DESTINATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STATION;
import hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ScheduleGenerator extends SyntheticGenerator {

	/**
	 * It equals to the sizeStep * size of the model.
	 */
	protected int maxNodes;

	/**
	 * Represents the current number of nodes.
	 */
	protected int nodes;

	protected int sizeStep = 30000;

	/**
	 * The number of initially created isolated nodes. It must be higher
	 * than m.
	 */
	protected int initStations;

	/**
	 * The number of initially created schedule nodes connecting to a random
	 * subset of the initStations.
	 */
	protected int initSchedules;

	/**
	 * The number of edges that will be drawn for every new attached node.
	 */
	protected int m;

	public ScheduleGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	public void generate() throws Exception {
		switch (generatorConfig.getModelType()) {
		case SCHEDULE_REAL:
			fg.initModel();
			parseModel();
			fg.persistModel();
			break;
		case SCHEDULE_SCALE_FREE:
			System.out.print("Generating scale-free model, generator: " + fg.syntax() + ", size: "
					+ generatorConfig.getSize() + "... ");
			initializeConstants();
			fg.initModel();
			generateModel();
			fg.persistModel();
			System.out.println("Done!");
			break;
		default:
			generateModel();
			fg.persistModel();
		}
	}

	@Override
	protected void initializeConstants() {
		maxNodes = generatorConfig.getSize() * sizeStep;
		m = 10;
		initStations = 15;
		initSchedules = 3;

	}

	protected void generateModel() throws FileNotFoundException, IOException {
		// node(index) - degree pairs
		ArrayList<Node> stations = new ArrayList<Node>();
		LinkedList<Node> schedules = new LinkedList<Node>();
		int stationDegrees = 0;
		int scheduleDegrees = 0;
		for (int i = 0; i < initStations; i++) {
			stations.add(new Node(fg.createVertex(STATION), 0));
		}

		final ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < initSchedules; i++) {
			indices.addAll(getRandomElements(stations, m));
			for (Integer index : indices) {
				schedules.add(new Node(fg.createVertex(SCHEDULE), 0));
				fg.createEdge(DESTINATIONS, schedules.getLast().obj, stations.get(index).obj);
				stations.get(index).degree++;
				stationDegrees++;
				schedules.getLast().degree++;
				scheduleDegrees++;
			}
			indices.clear();
		}

		nodes = initStations + initSchedules;
		// while (nodes <= maxNodes) {
		// for (int i = 0; i < 30; i++) {
		//
		// }
		// }
	}

	public void parseModel() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File(generatorConfig.getModelPathNameWithoutExtension() + ".json"));

		JsonNode trains = root.get("Trains");
		for (JsonNode train : trains) {
			fg.createVertex(ScheduleConstants.TRAIN);
		}
	}

	protected ArrayList<Integer> getRandomElements(final ArrayList<Node> list, final int amount) {
		final int size = list.size();
		if (amount > size) {
			throw new IllegalArgumentException("Amount is bigger than the size of the list!");
		}

		ArrayList<Integer> indices = new ArrayList<Integer>();
		int count = 0;
		int index = 0;
		while (count < amount) {
			index = random.nextInt(size);
			if (!indices.contains(index)) {
				indices.add(index);
				count++;
			}
		}
		return indices;

	}

	private class Node {

		private Object obj;

		private int degree;

		private Node(Object obj, int degree) {
			this.obj = obj;
			this.degree = degree;
		}
	}
}