package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.GATHERS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REGION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSORS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigWrapper;

public class MinimalSemaphoreNeighborGenerator extends MinimalModelGenerator {

	public MinimalSemaphoreNeighborGenerator(final ModelSerializer serializer, final GeneratorConfigWrapper generatorConfigWrapper) {
		super(serializer, generatorConfigWrapper);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Object region = serializer.createVertex(REGION);
		final Object semaphore = serializer.createVertex(SEMAPHORE);

		final Map<String, Object> routeOutgoingEdges = ImmutableMap.of(EXIT, semaphore);
		final Map<String, ? extends Object> emptyMap = Collections.emptyMap();
		final Object route1 = serializer.createVertex(ROUTE, emptyMap, routeOutgoingEdges);

		final Object route2 = serializer.createVertex(ROUTE);
		final Object sensor1 = serializer.createVertex(SENSOR);
		final Object sensor2 = serializer.createVertex(SENSOR);
		final Object te1 = serializer.createVertex(SEGMENT);
		final Object te2 = serializer.createVertex(SEGMENT);
		
		serializer.createEdge(SENSORS, region, sensor1);
		serializer.createEdge(SENSORS, region, sensor2);
		serializer.createEdge(ELEMENTS, region, te1);
		serializer.createEdge(ELEMENTS, region, te2);
		
		serializer.createEdge(ModelConstants.SEMAPHORES, te1, semaphore);

		serializer.createEdge(GATHERS, route1, sensor1);
		serializer.createEdge(GATHERS, route2, sensor2);
		serializer.createEdge(MONITORED_BY, te1, sensor1);
		serializer.createEdge(MONITORED_BY, te2, sensor2);
		serializer.createEdge(CONNECTS_TO, te1, te2);
	}

}
