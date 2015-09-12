package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_EDGE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class MinimalRouteSensorGenerator extends MinimalModelGenerator {

	public MinimalRouteSensorGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Object route = serializer.createVertex(ROUTE);
		final Object sensor = serializer.createVertex(SENSOR);
		final Object sw = serializer.createVertex(SWITCH);

		final Map<String, Object> swPOutgoingEdges = ImmutableMap.of(SWITCH_EDGE, sw);
		final Map<String, Object> swPIncomingEdges = ImmutableMap.of(FOLLOWS, route);
		final Object swP = serializer.createVertex(SWITCHPOSITION, Collections.emptyMap(), swPOutgoingEdges, swPIncomingEdges);

		serializer.createEdge(SENSOR_EDGE, sw, sensor);
		// this is required by the EMF serializer to fix the containment hierarchy
		serializer.createEdge(DEFINED_BY, null, sensor);
	}

}
