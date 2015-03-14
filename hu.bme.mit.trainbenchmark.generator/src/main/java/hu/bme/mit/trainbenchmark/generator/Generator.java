/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_ROUTEDEFINITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE_SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT_LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL_CURRENTSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION_SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION_SWITCHSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_CURRENTSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_SENSOR;
import hu.bme.mit.trainbenchmark.constants.SignalState;
import hu.bme.mit.trainbenchmark.constants.SwitchState;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

public abstract class Generator {

	// id
	protected long id = 0L;

	// static configuration
	protected GeneratorConfig generatorConfig;

	// dynamic configuration
	protected int maxSegments;
	protected int maxRoutes;
	protected int maxSwitchPositions;
	protected int maxSensors;

	protected int posLengthErrorPercent;
	protected int switchSensorErrorPercent;
	protected int routeSensorErrorPercent;
	protected int signalNeighborErrorPercent;
	protected int switchSetErrorPercent;
	protected int connectedSegmentsErrorPercent;

	protected Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);

	protected int nextRandom() {
		return random.nextInt(100);
	}

	protected static final Map<String, Object> emptyMap = Collections.emptyMap();
	protected static int MAX_SEGMENT_LENGTH = 1000;

	public void generateModels() throws FileNotFoundException, IOException {
		System.out.print("Generating instance model, generator: " + syntax() + ", size: " + generatorConfig.getSize() + "... ");
		initializeConstants();
		initModel();
		generateModel();
		persistModel();
		System.out.println("Done.");
	}

	protected abstract String syntax();

	private void initializeConstants() {
		switch (generatorConfig.getScenario()) {
		case "User":
			maxSegments = 5;
			maxRoutes = 20 * generatorConfig.getSize();
			maxSwitchPositions = 20;
			maxSensors = 10;
			posLengthErrorPercent = 2;
			switchSensorErrorPercent = 2;
			routeSensorErrorPercent = 2;
			signalNeighborErrorPercent = 7;
			switchSetErrorPercent = 2;
			connectedSegmentsErrorPercent = 5;
			break;
		case "Repair":
			maxSegments = 5;
			maxRoutes = 20 * generatorConfig.getSize();
			maxSwitchPositions = 20;
			maxSensors = 10;
			posLengthErrorPercent = 10;
			switchSensorErrorPercent = 4;
			routeSensorErrorPercent = 10;
			signalNeighborErrorPercent = 8;
			switchSetErrorPercent = 10;
			connectedSegmentsErrorPercent = 5;
			break;
		case "Test":
			maxSegments = 5;
			maxRoutes = 2 * generatorConfig.getSize();
			maxSwitchPositions = 5;
			maxSensors = 10;
			connectedSegmentsErrorPercent = 5;
			break;
		default:
			throw new UnsupportedOperationException("Scenario not supported.");
		}
	}

	protected abstract void initModel() throws IOException;

	protected void generateModel() throws FileNotFoundException, IOException {
		Object prevSig = null;
		Object firstSig = null;
		List<Object> firstTracks = null;
		List<Object> prevTracks = null;

		for (long i = 0; i < maxRoutes; i++) {
			beginRoute();

			if (prevSig == null) {
				final Map<String, Object> signalAttributes = ImmutableMap.<String, Object> of(SIGNAL_CURRENTSTATE, SignalState.GO);

				prevSig = createVertex(SIGNAL, signalAttributes);
				firstSig = prevSig;
			}

			Object sig2;
			if (i != maxRoutes - 1) {
				final Map<String, Object> signalAttributes = ImmutableMap.<String, Object> of(SIGNAL_CURRENTSTATE, SignalState.GO);

				sig2 = createVertex(SIGNAL, signalAttributes);
			} else {
				sig2 = firstSig;
			}

			final Object entry = (nextRandom() >= signalNeighborErrorPercent) ? prevSig : -1L;
			final Object exit = (nextRandom() >= signalNeighborErrorPercent) ? sig2 : -1L;

			final ImmutableMap<String, Object> routeReferences = ImmutableMap.<String, Object> of(ROUTE_ENTRY, entry, ROUTE_EXIT, exit);
			final Object route = createVertex(ROUTE, emptyMap, routeReferences);

			final int swps = random.nextInt(maxSwitchPositions);

			final List<Object> currTracks = new ArrayList<>();

			for (int j = 0; j < swps; j++) {
				final Object sw = createVertex(SWITCH);
				currTracks.add(sw);

				final int sensors = random.nextInt(maxSensors);

				for (int k = 0; k < sensors; k++) {
					final Object sen = createVertex(SENSOR);

					if (nextRandom() >= switchSensorErrorPercent) {
						createEdge(TRACKELEMENT_SENSOR, sw, sen);
						if (nextRandom() >= routeSensorErrorPercent) {
							createEdge(ROUTE_ROUTEDEFINITION, route, sen);
						}
					}

					for (int m = 0; m < maxSegments; m++) {
						createSegment(currTracks, sen);
					}
					
					// creates another extra segment
					if (nextRandom() < connectedSegmentsErrorPercent){
						createSegment(currTracks, sen);
					}
				}

				final int stateNumber = random.nextInt(4);
				final SwitchState stateEnum = SwitchState.values()[stateNumber];
				setAttribute(SWITCH, sw, SWITCH_CURRENTSTATE, stateEnum);

				// the errorInjectedState may contain a bad value
				final int errorInjectedStateNumber = (nextRandom() < switchSetErrorPercent) ? 3 - stateNumber : stateNumber;
				final SwitchState errorInjectedStateEnum = SwitchState.values()[errorInjectedStateNumber];
				final ImmutableMap<String, Object> switchPosititonAttributes = ImmutableMap.<String, Object> of(SWITCHPOSITION_SWITCHSTATE,
						errorInjectedStateEnum);
				final Map<String, Object> switchPositionOutgoingEdges = ImmutableMap.<String, Object> of(SWITCHPOSITION_SWITCH, sw);
				final Map<String, Object> switchPositionIncomingEdges = ImmutableMap.<String, Object> of(ROUTE_SWITCHPOSITION, route);
				createVertex(SWITCHPOSITION, switchPosititonAttributes, switchPositionOutgoingEdges, switchPositionIncomingEdges);
			}

			Object prevte = null;
			for (final Object trackelement : currTracks) {
				if (prevte != null) {
					createEdge(TRACKELEMENT_CONNECTSTO, prevte, trackelement);
				}
				prevte = trackelement;
			}

			if (prevTracks != null && prevTracks.size() > 0 && currTracks.size() > 0) {
				createEdge(TRACKELEMENT_CONNECTSTO, prevTracks.get(prevTracks.size() - 1), currTracks.get(0));
			}

			// Loop the last track element of the last route to the first track
			// element of the first route.
			if (i == maxRoutes - 1) {
				if (currTracks != null && currTracks.size() > 0 && firstTracks.size() > 0) {
					createEdge(TRACKELEMENT_CONNECTSTO, currTracks.get(currTracks.size() - 1), firstTracks.get(0));
				}
			}

			if (prevTracks == null) {
				firstTracks = currTracks;
			}

			prevTracks = currTracks;
			prevSig = sig2;

			endRoute();
		}
	}

	private void createSegment(final List<Object> currTracks, final Object sen) throws IOException{
		final int segmentLength = ((nextRandom() < posLengthErrorPercent) ? -1 : 1) * random.nextInt(MAX_SEGMENT_LENGTH);

		final Map<String, Object> segmentAttributes = ImmutableMap.<String, Object> of(SEGMENT_LENGTH, segmentLength);
		final Object seg = createVertex(SEGMENT, segmentAttributes);

		createEdge(TRACKELEMENT_SENSOR, seg, sen);
		currTracks.add(seg);
	}

	protected abstract void persistModel() throws IOException;

	// the createVertex() methods with fewer arguments are final 
	
	protected final Object createVertex(final String type) throws IOException {
		return createVertex(type, emptyMap);
	}

	protected final Object createVertex(final String type, final Map<String, Object> attributes) throws IOException {
		return createVertex(type, attributes, emptyMap);
	}

	protected final Object createVertex(final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges)
			throws IOException {
		return createVertex(type, attributes, outgoingEdges, emptyMap);
	}

	protected final Object createVertex(final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges,
			final Map<String, Object> incomingEdges) throws IOException {
		id++;
		return createVertex(id, type, attributes, outgoingEdges, incomingEdges);
	}

	protected abstract Object createVertex(final long id, final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException;

	protected abstract void createEdge(String label, Object from, Object to) throws IOException;

	protected abstract void setAttribute(String type, Object node, String key, Object value) throws IOException;

	protected void beginRoute() throws IOException {
	};

	protected void endRoute() throws IOException {
	}

}
