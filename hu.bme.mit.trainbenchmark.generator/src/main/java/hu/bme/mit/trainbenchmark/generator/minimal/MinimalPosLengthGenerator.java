package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_EDGE;
import static hu.bme.mit.trainbenchmark.constants.Position.LEFT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class MinimalPosLengthGenerator extends MinimalModelGenerator {

	public MinimalPosLengthGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Map<String, ? extends Object> segmentAttributes = ImmutableMap.of(LENGTH, 120);
		final Object segment = serializer.createVertex(SEGMENT, segmentAttributes);

		// this is required by the EMF serializer to fix the containment hierarchy
		serializer.createEdge(SENSOR_EDGE, segment, null);

		final Map<String, ? extends Object> switchAttributes = ImmutableMap.of(CURRENTPOSITION, LEFT);
		final Object sw = serializer.createVertex(SWITCH, switchAttributes);

		serializer.createEdge(CONNECTSTO, segment, sw);
		serializer.createEdge(SWITCH_EDGE, sw, null);
	}

}
