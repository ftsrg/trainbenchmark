package hu.bme.mit.trainbenchmark.generator.scalable;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.GATHERS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORES;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSORS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TARGET;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
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
			boolean firstSegment = true;

			serializer.beginTransaction();

			if (prevSemaphore == null) {
				final Map<String, Object> semaphoreAttributes = new HashMap<>();
				semaphoreAttributes.put(SIGNAL, Signal.GO);

				prevSemaphore = serializer.createVertex(SEMAPHORE, semaphoreAttributes);
				firstSemaphore = prevSemaphore;
			}

			Object semaphore;
			if (i != maxRoutes - 1) {
				final Map<String, Object> semaphoreAttributes = new HashMap<>();
				semaphoreAttributes.put(SIGNAL, Signal.GO);
				semaphore = serializer.createVertex(SEMAPHORE, semaphoreAttributes);
			} else {
				semaphore = firstSemaphore;
			}

			// the semaphoreNeighborErrorPercent
			final boolean semaphoreNeighborError1 = nextRandom() < semaphoreNeighborErrorPercent;
			final Object entry = semaphoreNeighborError1 ? null : prevSemaphore;
			final Object exit = semaphore;

			final Map<String, Object> routeOutgoingEdges = new HashMap<>();
			routeOutgoingEdges.put(ENTRY, entry);
			routeOutgoingEdges.put(EXIT, exit);

			final Object route = serializer.createVertex(ROUTE, Collections.<String, Object> emptyMap(), routeOutgoingEdges);
			final Object region = serializer.createVertex(ModelConstants.REGION);

			final int swps = random.nextInt(maxSwitchPositions - 1) + 1;
			final List<Object> currentTrack = new ArrayList<>();

			for (int j = 0; j < swps; j++) {
				final Object sw = serializer.createVertex(SWITCH);

				serializer.createEdge(ELEMENTS, region, sw);
				currentTrack.add(sw);

				final int sensors = random.nextInt(maxSensors - 1) + 1;

				for (int k = 0; k < sensors; k++) {
					final Object sensor = serializer.createVertex(SENSOR);
					serializer.createEdge(SENSORS, region, sensor);

					// add "monitored by" edge from switch to sensor
					final boolean switchSensorError = nextRandom() < switchSensorErrorPercent;
					if (!switchSensorError) {
						serializer.createEdge(MONITORED_BY, sw, sensor);

						// add "gathers" edge from route to sensor
						final boolean routeSensorError = nextRandom() < routeSensorErrorPercent;					
						if (!routeSensorError) {
							serializer.createEdge(GATHERS, route, sensor);
						}
					}
					
					// generate segments
					for (int m = 0; m < maxSegments; m++) {
						Object segment = createSegment(currentTrack, sensor, region);

						if (firstSegment) {
							serializer.createEdge(SEMAPHORES, segment, semaphore);
							firstSegment = false;
						}
					}
					
					// create another extra segment
					if (nextRandom() < connectedSegmentsErrorPercent) {
						createSegment(currentTrack, sensor, region);
					}
				}

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
				switchPositionOutgoingEdges.put(TARGET, sw);

				final Map<String, Object> switchPositionIncomingEdges = new HashMap<>();
				switchPositionIncomingEdges.put(FOLLOWS, route);

				serializer.createVertex(SWITCHPOSITION, switchPosititonAttributes, switchPositionOutgoingEdges,
						switchPositionIncomingEdges);
			}

			Object prevte = null;
			for (final Object trackelement : currentTrack) {
				if (prevte != null) {
					serializer.createEdge(CONNECTS_TO, prevte, trackelement);
				}
				prevte = trackelement;
			}

			if (prevTracks != null && prevTracks.size() > 0 && currentTrack.size() > 0) {
				serializer.createEdge(CONNECTS_TO, prevTracks.get(prevTracks.size() - 1), currentTrack.get(0));
			}

			// Loop the last track element of the last route to the first track
			// element of the first route.
			if (i == maxRoutes - 1) {
				if (currentTrack != null && currentTrack.size() > 0 && firstTracks.size() > 0) {
					serializer.createEdge(CONNECTS_TO, currentTrack.get(currentTrack.size() - 1), firstTracks.get(0));
				}
			}

			if (prevTracks == null) {
				firstTracks = currentTrack;
			}

			prevTracks = currentTrack;
			prevSemaphore = semaphore;

			serializer.endTransaction();
		}
	}

	private Object createSegment(final List<Object> currTracks, final Object sensor, final Object region) throws IOException {
		final boolean posLengthError = nextRandom() < posLengthErrorPercent;
		final int segmentLength = ((posLengthError ? -1 : 1) * random.nextInt(MAX_SEGMENT_LENGTH)) + 1;

		final Map<String, Object> segmentAttributes = new HashMap<>();
		segmentAttributes.put(LENGTH, segmentLength);
		final Object segment = serializer.createVertex(SEGMENT, segmentAttributes);

		serializer.createEdge(ELEMENTS, region, segment);
		serializer.createEdge(MONITORED_BY, segment, sensor);
		currTracks.add(segment);
		return segment;
	}

}
