package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class MinimalSemaphoreNeighborGenerator extends MinimalModelGenerator {

	public MinimalSemaphoreNeighborGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Object semaphore = serializer.createVertex(SEMAPHORE);

		final Map<String, Object> routeOutgoingEdges = ImmutableMap.of(EXIT, semaphore);
		final Map<String, ? extends Object> emptyMap = Collections.emptyMap();
		final Object route1 = serializer.createVertex(ROUTE, emptyMap, routeOutgoingEdges);

		final Object route2 = serializer.createVertex(ROUTE);
		final Object sensor1 = serializer.createVertex(SENSOR);
		final Object sensor2 = serializer.createVertex(SENSOR);
		final Object te1 = serializer.createVertex(SEGMENT);
		final Object te2 = serializer.createVertex(SEGMENT);

		// this is required by the EMF serializer to fix the containment hierarchy
		serializer.createEdge(ENTRY, route2, null);
		serializer.createEdge(DEFINED_BY, route1, sensor1);
		serializer.createEdge(DEFINED_BY, route2, sensor2);
		serializer.createEdge(SENSOR_EDGE, te1, sensor1);
		serializer.createEdge(SENSOR_EDGE, te2, sensor2);
		serializer.createEdge(CONNECTSTO, te1, te2);
	}

}
