package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;

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
		final Map<String, Object> segmentAttributes = ImmutableMap.of(LENGTH, -1);
		serializer.createVertex(SEGMENT, segmentAttributes);
	}

}
