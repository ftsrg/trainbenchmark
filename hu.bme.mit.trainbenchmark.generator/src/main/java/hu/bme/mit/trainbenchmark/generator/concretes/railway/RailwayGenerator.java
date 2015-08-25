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

package hu.bme.mit.trainbenchmark.generator.concretes.railway;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SWITCH_EDGE;
import hu.bme.mit.trainbenchmark.constants.railway.Position;
import hu.bme.mit.trainbenchmark.constants.railway.Signal;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.SyntheticGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RailwayGenerator extends SyntheticGenerator {

	// dynamic configuration
	protected int maxSegments;
	protected int maxRoutes;
	protected int maxSwitchPositions;
	protected int maxSensors;

	protected int posLengthErrorPercent;
	protected int switchSensorErrorPercent;
	protected int routeSensorErrorPercent;
	protected int semaphoreNeighborErrorPercent;
	protected int switchSetErrorPercent;
	protected int connectedSegmentsErrorPercent;

	protected static int MAX_SEGMENT_LENGTH = 1000;

	public RailwayGenerator(FormatGenerator formatGenerator, GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	protected int nextRandom() {
		return random.nextInt(100);
	}

	// @Override
	// public void generate() throws Exception {
	// System.out.print("Generating instance model, generator: " +
	// fg.syntax() + ", size: "
	// + generatorConfig.getSize() + "... ");
	// initializeConstants();
	// fg.initModel();
	// generateModel();
	// fg.persistModel();
	// System.out.println("Done.");
	// }

	@Override
	protected void initializeConstants() {
		switch (generatorConfig.getScenario()) {
		case INJECT:
			maxSegments = 5;
			maxRoutes = 5 * generatorConfig.getSize();
			maxSwitchPositions = 20;
			maxSensors = 10;
			connectedSegmentsErrorPercent = 5;
			posLengthErrorPercent = 2;
			routeSensorErrorPercent = 4;
			semaphoreNeighborErrorPercent = 7;
			switchSensorErrorPercent = 2;
			switchSetErrorPercent = 8;
			break;
		case REPAIR:
			maxSegments = 5;
			maxRoutes = 5 * generatorConfig.getSize();
			maxSwitchPositions = 20;
			maxSensors = 10;
			connectedSegmentsErrorPercent = 5;
			posLengthErrorPercent = 10;
			routeSensorErrorPercent = 10;
			semaphoreNeighborErrorPercent = 8;
			switchSensorErrorPercent = 4;
			switchSetErrorPercent = 10;
			break;
		default:
			throw new UnsupportedOperationException("Scenario not supported.");
		}
	}

	protected void generateModel() throws FileNotFoundException, IOException {
		Object prevSemaphore = null;
		Object firstSemaphore = null;
		List<Object> firstTracks = null;
		List<Object> prevTracks = null;

		for (long i = 0; i < maxRoutes; i++) {
			fg.startTransaction();

			if (prevSemaphore == null) {
				final Map<String, Object> semaphoreAttributes = new HashMap<>();
				semaphoreAttributes.put(SIGNAL, Signal.GO);

				prevSemaphore = fg.createVertex(SEMAPHORE, semaphoreAttributes);
				firstSemaphore = prevSemaphore;
			}

			Object semaphore2;
			if (i != maxRoutes - 1) {
				final Map<String, Object> semaphoreAttributes = new HashMap<>();
				semaphoreAttributes.put(SIGNAL, Signal.GO);
				semaphore2 = fg.createVertex(SEMAPHORE, semaphoreAttributes);
			} else {
				semaphore2 = firstSemaphore;
			}

			// the semaphoreNeighborErrorPercent
			final boolean semaphoreNeighborError1 = nextRandom() < semaphoreNeighborErrorPercent;
			final Object entry = semaphoreNeighborError1 ? null : prevSemaphore;
			final Object exit = semaphore2;

			final Map<String, Object> routeReferences = new HashMap<>();
			routeReferences.put(ENTRY, entry);
			routeReferences.put(EXIT, exit);

			final Object route = fg.createVertex(ROUTE, emptyMap, routeReferences);

			final int swps = random.nextInt(maxSwitchPositions);
			final List<Object> currentTrack = new ArrayList<>();

			for (int j = 0; j < swps; j++) {
				final Object sw = fg.createVertex(SWITCH);
				currentTrack.add(sw);

				final int sensors = random.nextInt(maxSensors - 1) + 1;

				Object lastSensor = null;
				for (int k = 0; k < sensors; k++) {
					final Object sensor = fg.createVertex(SENSOR);

					// add "sensors" edge from route to
					// sensor
					final boolean routeSensorError = nextRandom() < routeSensorErrorPercent;
					final Object sourceRoute = routeSensorError ? null : route;
					fg.createEdge(DEFINED_BY, sourceRoute, sensor);

					for (int m = 0; m < maxSegments; m++) {
						createSegment(currentTrack, sensor);
					}

					// creates another extra segment
					if (nextRandom() < connectedSegmentsErrorPercent) {
						createSegment(currentTrack, sensor);
					}

					lastSensor = sensor;
				}
				// add "sensor" edge from switch to sensor
				final boolean switchSensorError = nextRandom() < switchSensorErrorPercent;
				final Object targetSensor = switchSensorError ? null : lastSensor;
				fg.createEdge(SENSOR_EDGE, sw, targetSensor);

				final int stateNumber = random.nextInt(4);
				final Position stateEnum = Position.values()[stateNumber];
				fg.setAttribute(SWITCH, sw, CURRENTPOSITION, stateEnum);

				// the errorInjectedState may contain a bad
				// value
				final boolean switchSetError = nextRandom() < switchSetErrorPercent;
				final int errorInjectedStateNumber = switchSetError ? 3 - stateNumber : stateNumber;
				final Position errorInjectedStateEnum = Position.values()[errorInjectedStateNumber];
				final Map<String, Object> switchPosititonAttributes = new HashMap<>();
				switchPosititonAttributes.put(POSITION, errorInjectedStateEnum);

				final Map<String, Object> switchPositionOutgoingEdges = new HashMap<>();
				switchPositionOutgoingEdges.put(SWITCH_EDGE, sw);

				final Map<String, Object> switchPositionIncomingEdges = new HashMap<>();
				switchPositionIncomingEdges.put(FOLLOWS, route);

				fg.createVertex(SWITCHPOSITION, switchPosititonAttributes, switchPositionOutgoingEdges,
						switchPositionIncomingEdges);
			}

			Object prevte = null;
			for (final Object trackelement : currentTrack) {
				if (prevte != null) {
					fg.createEdge(CONNECTSTO, prevte, trackelement);
				}
				prevte = trackelement;
			}

			if (prevTracks != null && prevTracks.size() > 0 && currentTrack.size() > 0) {
				fg.createEdge(CONNECTSTO, prevTracks.get(prevTracks.size() - 1), currentTrack.get(0));
			}

			// Loop the last track element of the last route to the
			// first track
			// element of the first route.
			if (i == maxRoutes - 1) {
				if (currentTrack != null && currentTrack.size() > 0 && firstTracks.size() > 0) {
					fg.createEdge(CONNECTSTO, currentTrack.get(currentTrack.size() - 1),
							firstTracks.get(0));
				}
			}

			if (prevTracks == null) {
				firstTracks = currentTrack;
			}

			prevTracks = currentTrack;
			prevSemaphore = semaphore2;

			fg.endTransaction();
		}
	}

	private void createSegment(final List<Object> currTracks, final Object sen) throws IOException {
		final boolean posLengthError = nextRandom() < posLengthErrorPercent;
		final int segmentLength = ((posLengthError ? -1 : 1) * random.nextInt(MAX_SEGMENT_LENGTH)) + 1;

		final Map<String, Object> segmentAttributes = new HashMap<>();
		segmentAttributes.put(LENGTH, segmentLength);
		final Object seg = fg.createVertex(SEGMENT, segmentAttributes);

		fg.createEdge(SENSOR_EDGE, seg, sen);
		currTracks.add(seg);
	}

}
