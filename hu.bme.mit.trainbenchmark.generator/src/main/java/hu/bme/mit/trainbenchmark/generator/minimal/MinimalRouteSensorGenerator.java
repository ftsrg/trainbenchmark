package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REGION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSORS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TARGET;

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
		final Object region = serializer.createVertex(REGION);

		final Object route = serializer.createVertex(ROUTE);
		final Object sensor = serializer.createVertex(SENSOR);
		final Object sw = serializer.createVertex(SWITCH);

		serializer.createEdge(SENSORS, region, sensor);
		serializer.createEdge(ELEMENTS, region, sw);
		
		final Map<String, Object> swPOutgoingEdges = ImmutableMap.of(TARGET, sw);
		final Map<String, ? extends Object> emptyMap = Collections.emptyMap();
		final Object swP = serializer.createVertex(SWITCHPOSITION, emptyMap, swPOutgoingEdges);
		serializer.createEdge(FOLLOWS, route, swP);
		
		serializer.createEdge(MONITORED_BY, sw, sensor);
	}

}
