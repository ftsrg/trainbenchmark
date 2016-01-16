package hu.bme.mit.trainbenchmark.generator.scalable;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.GATHERS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_EDGE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.constants.Signal;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class ScalableModelGenerator extends ModelGenerator {

	public static final int MAX_SEGMENT_LENGTH = 1000;

	protected int maxSegments = 5;
	protected int maxRoutes;
	protected int maxSwitchPositions = 20;
	protected int maxSensors = 10;

	protected int posLengthErrorPercent = 0;
	protected int switchSensorErrorPercent = 0;
	protected int routeSensorErrorPercent = 0;
	protected int semaphoreNeighborErrorPercent = 0;
	protected int switchSetErrorPercent = 0;
	protected int connectedSegmentsErrorPercent = 0;

	protected Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);

	public ScalableModelGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);

		maxRoutes = 5 * generatorConfig.getSize();
		switch (generatorConfig.getScenario()) {
		case BATCH:
			// set all error percents to 0
			break;
		case INJECT:
			connectedSegmentsErrorPercent = 5;
			posLengthErrorPercent = 2;
			routeSensorErrorPercent = 4;
			semaphoreNeighborErrorPercent = 7;
			switchSensorErrorPercent = 2;
			switchSetErrorPercent = 8;
			break;
		case REPAIR:
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

	protected int nextRandom() {
		return random.nextInt(100);
	}

	@Override
	protected void constructModel() throws FileNotFoundException, IOException {
		Object prevSemaphore = null;
		Object firstSemaphore = null;
		List<Object> firstTracks = null;
		List<Object> prevTracks = null;

		for (long i = 0; i < maxRoutes; i++) {
			serializer.beginTransaction();

			if (prevSemaphore == null) {
				final Map<String, Object> semaphoreAttributes = new HashMap<>();
				semaphoreAttributes.put(SIGNAL, Signal.GO);

				prevSemaphore = serializer.createVertex(SEMAPHORE, semaphoreAttributes);
				firstSemaphore = prevSemaphore;
			}

			Object semaphore2;
			if (i != maxRoutes - 1) {
				final Map<String, Object> semaphoreAttributes = new HashMap<>();
				semaphoreAttributes.put(SIGNAL, Signal.GO);
				semaphore2 = serializer.createVertex(SEMAPHORE, semaphoreAttributes);
			} else {
				semaphore2 = firstSemaphore;
			}

			// the semaphoreNeighborErrorPercent
			final boolean semaphoreNeighborError1 = nextRandom() < semaphoreNeighborErrorPercent;
			final Object entry = semaphoreNeighborError1 ? null : prevSemaphore;
			final Object exit = semaphore2;

			final Map<String, Object> routeOutgoingEdges = new HashMap<>();
			routeOutgoingEdges.put(ENTRY, entry);
			routeOutgoingEdges.put(EXIT, exit);

			final Object route = serializer.createVertex(ROUTE, Collections.<String, Object> emptyMap(), routeOutgoingEdges);

			final int swps = random.nextInt(maxSwitchPositions);
			final List<Object> currentTrack = new ArrayList<>();

			for (int j = 0; j < swps; j++) {
				final Object sw = serializer.createVertex(SWITCH);
				currentTrack.add(sw);

				final int sensors = random.nextInt(maxSensors - 1) + 1;

				Object lastSensor = null;
				for (int k = 0; k < sensors; k++) {
					final Object sensor = serializer.createVertex(SENSOR);

					// add "sensors" edge from route to sensor
					final boolean routeSensorError = nextRandom() < routeSensorErrorPercent;
					final Object sourceRoute = routeSensorError ? null : route;
					serializer.createEdge(GATHERS, sourceRoute, sensor);

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
				serializer.createEdge(SENSOR_EDGE, sw, targetSensor);

				final int numberOfPositions = Position.values().length;
				final int positionOrdinal = random.nextInt(numberOfPositions);
				final Position position = Position.values()[positionOrdinal];
				serializer.setAttribute(SWITCH, sw, CURRENTPOSITION, position);

				// the errorInjectedState may contain a bad value
				final boolean switchSetError = nextRandom() < switchSetErrorPercent;
				final int invalidPositionOrdinal = switchSetError ? (numberOfPositions - 1) - positionOrdinal : positionOrdinal;
				final Position invalidPosition = Position.values()[invalidPositionOrdinal];
				final Map<String, Object> switchPosititonAttributes = new HashMap<>();
				switchPosititonAttributes.put(POSITION, invalidPosition);

				final Map<String, Object> switchPositionOutgoingEdges = new HashMap<>();
				switchPositionOutgoingEdges.put(SWITCH_EDGE, sw);

				final Map<String, Object> switchPositionIncomingEdges = new HashMap<>();
				switchPositionIncomingEdges.put(FOLLOWS, route);

				serializer.createVertex(SWITCHPOSITION, switchPosititonAttributes, switchPositionOutgoingEdges,
						switchPositionIncomingEdges);
			}

			Object prevte = null;
			for (final Object trackelement : currentTrack) {
				if (prevte != null) {
					serializer.createEdge(CONNECTSTO, prevte, trackelement);
				}
				prevte = trackelement;
			}

			if (prevTracks != null && prevTracks.size() > 0 && currentTrack.size() > 0) {
				serializer.createEdge(CONNECTSTO, prevTracks.get(prevTracks.size() - 1), currentTrack.get(0));
			}

			// Loop the last track element of the last route to the first track
			// element of the first route.
			if (i == maxRoutes - 1) {
				if (currentTrack != null && currentTrack.size() > 0 && firstTracks.size() > 0) {
					serializer.createEdge(CONNECTSTO, currentTrack.get(currentTrack.size() - 1), firstTracks.get(0));
				}
			}

			if (prevTracks == null) {
				firstTracks = currentTrack;
			}

			prevTracks = currentTrack;
			prevSemaphore = semaphore2;

			serializer.endTransaction();
		}
	}

	private void createSegment(final List<Object> currTracks, final Object sen) throws IOException {
		final boolean posLengthError = nextRandom() < posLengthErrorPercent;
		final int segmentLength = ((posLengthError ? -1 : 1) * random.nextInt(MAX_SEGMENT_LENGTH)) + 1;

		final Map<String, Object> segmentAttributes = new HashMap<>();
		segmentAttributes.put(LENGTH, segmentLength);
		final Object seg = serializer.createVertex(SEGMENT, segmentAttributes);

		serializer.createEdge(SENSOR_EDGE, seg, sen);
		currTracks.add(seg);
	}

}
