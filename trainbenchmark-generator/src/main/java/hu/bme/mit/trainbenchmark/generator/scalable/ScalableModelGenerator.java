package hu.bme.mit.trainbenchmark.generator.scalable;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ACTIVE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DOMAIN_ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REGION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REQUIRES;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableMap;

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

	public ScalableModelGenerator(final ModelSerializer<?> serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
		maxRoutes = 6 * generatorConfig.getConfigBase().getSize();
		switch (generatorConfig.getConfigBase().getScenario()) {
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
			semaphoreNeighborErrorPercent = 25;
			switchSensorErrorPercent = 18;
			switchSetErrorPercent = 15;
			break;
		default:
			throw new UnsupportedOperationException("Scenario not supported.");
		}
	}

	protected int nextRandom() {
		return random.nextInt(100);
	}

	final int moduleCount = 6;
	final int regionsAndRoutes = 1;
	final boolean alternateAllocation = true;

	private void setAllocation(List<Object> allocations, int i, Object o) throws IOException {
		Object allocation;
		if (alternateAllocation) {
			// select based on type
			if(o.getClass().getSimpleName().contains("Route") || o.getClass().getSimpleName().contains("Semaphore") || o.getClass().getSimpleName().contains("Region") ) {
				allocation = allocations.get(i % regionsAndRoutes);				
			} else {
				allocation = allocations.get(i % (moduleCount-regionsAndRoutes) + regionsAndRoutes);								
			}
		} else {
			// select based on route/region
			allocation = allocations.get((i % moduleCount-3) + 3);
		}

		// add edge
		serializer.createEdge(DOMAIN_ELEMENTS, allocation, o);
	}

	@Override
	protected void constructModel() throws FileNotFoundException, IOException {
		// computingmodule
//		{":id": 101,	":node": "BBB1",	":type": "ComputingModule",	"memoryMB": 1024,	"cpuMZH": 1000,	"replyTimeMaxMS": 2000,	"hostID": 1001,	"communicatesWith": []},
//		{":id": 102,	":node": "BBB2",	":type": "ComputingModule",	"memoryMB": 1024,	"cpuMZH": 1000,	"replyTimeMaxMS": 2000,	"hostID": 1002,	"communicatesWith": []},
//		{":id": 103,	":node": "BBB3",	":type": "ComputingModule",	"memoryMB": 1024,	"cpuMZH": 1000,	"replyTimeMaxMS": 2000,	"hostID": 1003,	"communicatesWith": []},
//		{":id": 104,	":node": "BBB4",	":type": "ComputingModule",	"memoryMB": 1024,	"cpuMZH": 1000,	"replyTimeMaxMS": 2000,	"hostID": 1004,	"communicatesWith": []},
//		{":id": 105,	":node": "BBB5",	":type": "ComputingModule",	"memoryMB": 1024,	"cpuMZH": 1000,	"replyTimeMaxMS": 2000,	"hostID": 1005,	"communicatesWith": []},
//		{":id": 106,	":node": "BBB6",	":type": "ComputingModule",	"memoryMB": 1024,	"cpuMZH": 1000,	"replyTimeMaxMS": 2000,	"hostID": 1006,	"communicatesWith": []},

		// computing module ids start from 10 million
		List<Object> computingModules = new ArrayList<>();
		for (int i = 1; i <= moduleCount; i++) {
			Map<String, Object> attributes = new ImmutableMap.Builder<String, Object>() //
					.put("id", 10*1000*1000 + i) //
					.put("node", "BBB" + i) //
					.put("memoryMB", 1024) //
					.put("cpuMHZ", 1000) //
					.put("replyTimeMaxMS", 2000) //
					.put("hostID", 1000 + i) //
					.build();
			Object computingModule = serializer.createVertex(ModelConstants.COMPUTING_MODULE, attributes);
			computingModules.add(computingModule);
		}

		// allocation
//		{":id": 201,	":node": "BBB1",	":type": "Allocation",	"computingModule": 101,	"domainElements": [15,16,17,19]},
//		{":id": 202,	":node": "BBB2",	":type": "Allocation",	"computingModule": 102,	"domainElements": [22,23,24,25,26,27,28,30]},
//		{":id": 203,	":node": "BBB3",	":type": "Allocation",	"computingModule": 103,	"domainElements": [1,12,18,20]},
//		{":id": 204,	":node": "BBB4",	":type": "Allocation",	"computingModule": 104,	"domainElements": [2,10,13,14,21]},
//		{":id": 205,	":node": "BBB5",	":type": "Allocation",	"computingModule": 105,	"domainElements": [4,5,6,7,8,9]},
//		{":id": 206,	":node": "BBB6",	":type": "Allocation",	"computingModule": 106,	"domainElements": [3,11,29]},

		// allocation ids start from 20 million
		List<Object> allocations = new ArrayList<>();
		for (int i = 1; i <= moduleCount; i++) {
			Map<String, Object> attributes = new ImmutableMap.Builder<String, Object>() //
					.put("id", 20*1000*1000 + i) //
					.put("node", "BBB" + i) //
					.build();
			Map<String, Object> edges = ImmutableMap.of(ModelConstants.COMPUTING_MODULE_EDGE, computingModules.get(i - 1));
			Object allocation = serializer.createVertex(ModelConstants.ALLOCATION, attributes, edges);
			allocations.add(allocation);
		}

		Object prevSemaphore = null;
		Object firstSemaphore = null;
		List<Object> firstTracks = null;
		List<Object> prevTracks = null;

		for (int i = 0; i < maxRoutes; i++) {
			boolean firstSegment = true;
			serializer.beginTransaction();

			if (prevSemaphore == null) {
				final Map<String, Object> semaphoreAttributes = ImmutableMap.of(SIGNAL, Signal.GO);

				prevSemaphore = serializer.createVertex(SEMAPHORE, semaphoreAttributes);
				setAllocation(allocations, i, prevSemaphore);

				firstSemaphore = prevSemaphore;
			}

			Object semaphore;
			if (i != maxRoutes - 1) {
				final Map<String, Object> semaphoreAttributes = ImmutableMap.of(SIGNAL, Signal.GO);
				semaphore = serializer.createVertex(SEMAPHORE, semaphoreAttributes);
				setAllocation(allocations, i, semaphore);
			} else {
				semaphore = firstSemaphore;
			}

			// the semaphoreNeighborErrorPercent
			final boolean semaphoreNeighborError1 = nextRandom() < semaphoreNeighborErrorPercent;
			final Object entry = semaphoreNeighborError1 ? null : prevSemaphore;
			final Object exit = semaphore;

			// the entry might be null, therefore we avoid using an ImmutableMap here
			final Map<String, Object> routeOutgoingEdges = new HashMap<>();
			routeOutgoingEdges.put(ENTRY, entry);
			routeOutgoingEdges.put(EXIT, exit);

			final Map<String, Object> routeAttributes = new HashMap<>();
			routeAttributes.put(ACTIVE, true);

			final Object route = serializer.createVertex(ROUTE, routeAttributes, routeOutgoingEdges);
			setAllocation(allocations, i, route);

			final Object region = serializer.createVertex(REGION);
			setAllocation(allocations, i, region);

			final int swPs = random.nextInt(maxSwitchPositions - 1) + 1;
			final List<Object> currentTrack = new ArrayList<>();
			final Set<Object> switches = new HashSet<>();
			for (int j = 0; j < swPs; j++) {
				final int numberOfPositions = Position.values().length;
				final int positionOrdinal = random.nextInt(numberOfPositions);
				final Position position = Position.values()[positionOrdinal];
				final Map<String, ? extends Object> swAttributes = ImmutableMap.of(CURRENTPOSITION, position);
				final Object sw = serializer.createVertex(SWITCH, swAttributes);
				setAllocation(allocations, i, sw);

				currentTrack.add(sw);
				switches.add(sw);

				// (region)-[:elements]->(sw)
				serializer.createEdge(ELEMENTS, region, sw);

				final int sensors = random.nextInt(maxSensors - 1) + 1;

				for (int k = 0; k < sensors; k++) {
					final Object sensor = serializer.createVertex(SENSOR);
					setAllocation(allocations, i, sensor);

					serializer.createEdge(SENSORS, region, sensor);

					// add "monitored by" edge from switch to sensor
					final boolean switchSensorError = nextRandom() < switchSensorErrorPercent;
					if (!switchSensorError) {
						serializer.createEdge(MONITORED_BY, sw, sensor);

						// add "requires" edge from route to sensor
						final boolean routeSensorError = nextRandom() < routeSensorErrorPercent;
						if (!routeSensorError) {
							serializer.createEdge(REQUIRES, route, sensor);
						}
					}

					// generate segments
					for (int m = 0; m < maxSegments; m++) {
						final Object segment = createSegment(currentTrack, sensor, region, allocations, i);

						if (firstSegment) {
							serializer.createEdge(SEMAPHORES, segment, semaphore);
							firstSegment = false;
						}
					}

					// create another extra segment
					if (nextRandom() < connectedSegmentsErrorPercent) {
						createSegment(currentTrack, sensor, region, allocations, i);
					}
				}

				// the errorInjectedState may contain a bad value
				final boolean switchSetError = nextRandom() < switchSetErrorPercent;
				final int invalidPositionOrdinal = switchSetError ? (numberOfPositions - 1) - positionOrdinal : positionOrdinal;
				final Position invalidPosition = Position.values()[invalidPositionOrdinal];

				final Map<String, Object> swPAttributes = ImmutableMap.of(POSITION, invalidPosition);
				final Map<String, Object> swPOutgoingEdges = ImmutableMap.of(TARGET, sw);
				final Object swP = serializer.createVertex(SWITCHPOSITION, swPAttributes, swPOutgoingEdges);
				setAllocation(allocations, i, swP);


				// (route)-[:follows]->(swP)
				serializer.createEdge(FOLLOWS, route, swP);
			}

			final Set<Integer> usedTracks = new HashSet<>();
			// create connectsTo (n:m) edges
			for (int j = 1; j < currentTrack.size(); ++j) {
				final Object current = currentTrack.get(j);
				if (usedTracks.contains(current))
					continue;

				serializer.createEdge(CONNECTS_TO, currentTrack.get(j - 1), current);

				if (switches.contains(current)) {
					final int segmentID = j + random.nextInt(currentTrack.size() - j);
					if (!usedTracks.contains(segmentID)) {
						// TODO check why this causes double edges
						// serializer.createEdge(CONNECTS_TO, current, currentTrack.get(segmentID));
						usedTracks.add(segmentID);
					}
				}
			}

			if (prevTracks != null && prevTracks.size() > 0 && currentTrack.size() > 0) {
				serializer.createEdge(CONNECTS_TO, prevTracks.get(prevTracks.size() - 1), currentTrack.get(0));
			}

			// loop the last track element of the last route to the first track element of the first route
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

	private Object createSegment(final List<Object> currTracks, final Object sensor, final Object region, final List<Object> allocations, final int i) throws IOException {
		final boolean posLengthError = nextRandom() < posLengthErrorPercent;
		final int segmentLength = ((posLengthError ? -1 : 1) * random.nextInt(MAX_SEGMENT_LENGTH)) + 1;

		final Map<String, Object> segmentAttributes = ImmutableMap.of(LENGTH, segmentLength);
		final Object segment = serializer.createVertex(SEGMENT, segmentAttributes);
		setAllocation(allocations, i, segment);


		// (region)-[:elements]->(segment)
		serializer.createEdge(ELEMENTS, region, segment);

		// (segment)-[:monitoredBy]->(sensor) monitoredBy n:m edge
		serializer.createEdge(MONITORED_BY, segment, sensor);
		currTracks.add(segment);
		return segment;
	}

}
