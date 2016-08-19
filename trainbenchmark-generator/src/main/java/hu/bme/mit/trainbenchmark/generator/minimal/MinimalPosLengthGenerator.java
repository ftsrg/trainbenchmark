package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REGION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigWrapper;

public class MinimalPosLengthGenerator extends MinimalModelGenerator {

	public MinimalPosLengthGenerator(final ModelSerializer<?> serializer, final GeneratorConfigWrapper generatorConfigWrapper) {
		super(serializer, generatorConfigWrapper);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Object region = serializer.createVertex(REGION);

		final Map<String, ? extends Object> segmentAttributes = ImmutableMap.of(LENGTH, -1);
		final Object segment = serializer.createVertex(SEGMENT, segmentAttributes);
		serializer.createEdge(ELEMENTS, region, segment);
	}

}
