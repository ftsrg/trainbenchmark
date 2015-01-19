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
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL_ACTUALSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION_SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION_SWITCHSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_ACTUALSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT_SENSOR;
import hu.bme.mit.trainbenchmark.constants.SignalStateKind;
import hu.bme.mit.trainbenchmark.constants.SwitchStateKind;
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

	// static configuration
	protected GeneratorConfig generatorConfig;

	// dynamic configuration
	protected int MAX_Segments;
	protected int MAX_Routes;
	protected int MAX_SwitchPositions;
	protected int MAX_Sensors;

	protected int posLengthErrorPercent;
	protected int switchSensorErrorPercent;
	protected int routeSensorErrorPercent;
	protected int signalNeighborErrorPercent;
	protected int switchSetErrorPercent;

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
			MAX_Segments = 5;
			MAX_Routes = 20 * generatorConfig.getSize();
			MAX_SwitchPositions = 20;
			MAX_Sensors = 10;
			posLengthErrorPercent = 2;
			switchSensorErrorPercent = 2;
			routeSensorErrorPercent = 2;
			signalNeighborErrorPercent = 7;
			switchSetErrorPercent = 2;
			break;
		case "XForm":
			MAX_Segments = 5;
			MAX_Routes = 20 * generatorConfig.getSize();
			MAX_SwitchPositions = 20;
			MAX_Sensors = 10;
			posLengthErrorPercent = 10;
			switchSensorErrorPercent = 4;
			routeSensorErrorPercent = 10;
			signalNeighborErrorPercent = 8;
			switchSetErrorPercent = 10;
			break;
		case "Test":
			MAX_Segments = 1;
			MAX_Routes = 2 * generatorConfig.getSize();
			MAX_SwitchPositions = 20;
			MAX_Sensors = 5;
			posLengthErrorPercent = 10;
			switchSensorErrorPercent = 4;
			routeSensorErrorPercent = 10;
			signalNeighborErrorPercent = 8;
			switchSetErrorPercent = 10;
			break;
		}
	}

	protected abstract void initModel() throws IOException;

	protected void generateModel() throws FileNotFoundException, IOException {
		Object prevSig = null;
		Object firstSig = null;
		List<Object> firstTracks = null;
		List<Object> prevTracks = null;

		for (long i = 0; i < MAX_Routes; i++) {
			beginRoute();

			if (prevSig == null) {
				final Map<String, Object> signalAttributes = ImmutableMap.<String, Object> of(SIGNAL_ACTUALSTATE,
						SignalStateKind.SIGNALSTATEKIND_GO);

				prevSig = createNode(SIGNAL, signalAttributes);
				firstSig = prevSig;
			}

			Object sig2;
			if (i != MAX_Routes - 1) {
				final Map<String, Object> signalAttributes = ImmutableMap.<String, Object> of(SIGNAL_ACTUALSTATE,
						SignalStateKind.SIGNALSTATEKIND_GO);

				sig2 = createNode(SIGNAL, signalAttributes);
			} else {
				sig2 = firstSig;
			}

			final Object entry = (nextRandom() >= signalNeighborErrorPercent) ? prevSig : 0L;
			final Object exit = (nextRandom() >= signalNeighborErrorPercent) ? sig2 : 0L;

			final ImmutableMap<String, Object> routeReferences = ImmutableMap.<String, Object> of(ROUTE_ENTRY, entry, ROUTE_EXIT, exit);
			final Object route = createNode(ROUTE, emptyMap, routeReferences);

			final int swps = random.nextInt(MAX_SwitchPositions);

			final List<Object> currTracks = new ArrayList<>();

			for (int j = 0; j < swps; j++) {
				final Object sw = createNode(SWITCH);
				currTracks.add(sw);

				final int sensors = random.nextInt(MAX_Sensors);

				for (int k = 0; k < sensors; k++) {
					final Object sen = createNode(SENSOR);

					if (nextRandom() >= switchSensorErrorPercent) {
						createEdge(TRACKELEMENT_SENSOR, sw, sen);
						if (nextRandom() >= routeSensorErrorPercent) {
							createEdge(ROUTE_ROUTEDEFINITION, route, sen);
						}
					}

					for (int m = 0; m < MAX_Segments; m++) {
						final int segmentLength = ((nextRandom() < posLengthErrorPercent) ? -1 : 1) * random.nextInt(MAX_SEGMENT_LENGTH);

						final Map<String, Object> segmentAttributes = ImmutableMap.<String, Object> of(SEGMENT_LENGTH, segmentLength);
						final Object seg = createNode(SEGMENT, segmentAttributes);

						createEdge(TRACKELEMENT_SENSOR, seg, sen);
						currTracks.add(seg);
					}
				}

				final int stateNumber = random.nextInt(4);
				final SwitchStateKind stateEnum = SwitchStateKind.values()[stateNumber];
				setAttribute(SWITCH, sw, SWITCH_ACTUALSTATE, stateEnum);

				// the errorInjectedState may contain a bad value
				final int errorInjectedStateNumber = (nextRandom() < switchSetErrorPercent) ? 3 - stateNumber : stateNumber;
				final SwitchStateKind errorInjectedStateEnum = SwitchStateKind.values()[errorInjectedStateNumber];
				final ImmutableMap<String, Object> switchPosititonAttributes = ImmutableMap.<String, Object> of(SWITCHPOSITION_SWITCHSTATE,
						errorInjectedStateEnum);
				final Map<String, Object> switchPositionOutgoingEdges = ImmutableMap.<String, Object> of(SWITCHPOSITION_SWITCH, sw);
				final Map<String, Object> switchPositionIncomingEdges = ImmutableMap.<String, Object> of(ROUTE_SWITCHPOSITION, route);
				createNode(SWITCHPOSITION, switchPosititonAttributes, switchPositionOutgoingEdges, switchPositionIncomingEdges);
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
			if (i == MAX_Routes - 1) {
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

	protected abstract void persistModel() throws IOException;

	protected abstract Object createNode(String type, Map<String, Object> attributes, Map<String, Object> outgoingEdges,
			Map<String, Object> incomingEdges) throws IOException;

	protected abstract void createEdge(String label, Object from, Object to) throws IOException;

	protected abstract void setAttribute(String type, Object node, String key, Object value) throws IOException;

	protected Object createNode(final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges)
			throws IOException {
		return createNode(type, attributes, outgoingEdges, emptyMap);
	}

	protected Object createNode(final String type, final Map<String, Object> attributes) throws IOException {
		return createNode(type, attributes, emptyMap);
	}

	protected Object createNode(final String type) throws IOException {
		return createNode(type, emptyMap);
	}

	protected void beginRoute() throws IOException {
	};

	protected void endRoute() {
	}

}
