package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.POSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_EDGE;
import static hu.bme.mit.trainbenchmark.constants.Position.LEFT;
import static hu.bme.mit.trainbenchmark.constants.Position.RIGHT;
import static hu.bme.mit.trainbenchmark.constants.Signal.GO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class MinimalSwitchSetGenerator extends MinimalModelGenerator {

	public MinimalSwitchSetGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Map<String, Object> semaphoreProperties = ImmutableMap.of(SIGNAL, GO);
		final Object semaphore = serializer.createVertex(SEMAPHORE, semaphoreProperties);
		final Object route = serializer.createVertex(ROUTE);
		final Map<String, Object> swPProperties = ImmutableMap.of(POSITION, LEFT);
		final Object swP = serializer.createVertex(SWITCHPOSITION, swPProperties);
		final Map<String, Object> swProperties = ImmutableMap.of(CURRENTPOSITION, RIGHT);
		final Object sw = serializer.createVertex(SWITCH, swProperties);

		serializer.createEdge(ENTRY, route, semaphore);
		serializer.createEdge(FOLLOWS, route, swP);
		serializer.createEdge(SWITCH_EDGE, swP, sw);
	}

}
