package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REGION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORES;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TARGET;
import static hu.bme.mit.trainbenchmark.constants.Position.DIVERGING;
import static hu.bme.mit.trainbenchmark.constants.Position.STRAIGHT;
import static hu.bme.mit.trainbenchmark.constants.Signal.GO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigWrapper;

public class MinimalSwitchSetGenerator extends MinimalModelGenerator {

	public MinimalSwitchSetGenerator(final ModelSerializer serializer, final GeneratorConfigWrapper generatorConfigWrapper) {
		super(serializer, generatorConfigWrapper);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Object region = serializer.createVertex(REGION);
		final Object segment = serializer.createVertex(SEGMENT);

		serializer.createEdge(ELEMENTS, region, segment);
		
		// semaphore
		final Map<String, ? extends Object> semaphoreProperties = ImmutableMap.of(SIGNAL, GO);
		final Object semaphore = serializer.createVertex(SEMAPHORE, semaphoreProperties);

		serializer.createEdge(SEMAPHORES, segment, semaphore);
		
		// route
		final Map<String, Object> routeOutgoingEdges = ImmutableMap.of(ENTRY, semaphore);
		final Map<String, ? extends Object> emptyMap = Collections.emptyMap();
		final Object route = serializer.createVertex(ROUTE, emptyMap, routeOutgoingEdges);

		// sw
		final Map<String, ? extends Object> swProperties = ImmutableMap.of(CURRENTPOSITION, DIVERGING);
		final Object sw = serializer.createVertex(SWITCH, swProperties);

		serializer.createEdge(ELEMENTS, region, sw);
		
		// swP
		final Map<String, ? extends Object> swPProperties = ImmutableMap.of(POSITION, STRAIGHT);
		final Map<String, Object> swPOutgoingEdges = ImmutableMap.of(TARGET, sw);
		final Object swP = serializer.createVertex(SWITCHPOSITION, swPProperties, swPOutgoingEdges);
		serializer.createEdge(FOLLOWS, route, swP);
	}

}
