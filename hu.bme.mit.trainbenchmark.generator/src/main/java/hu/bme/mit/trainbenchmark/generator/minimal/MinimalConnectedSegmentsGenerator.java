package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ELEMENTS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REGION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSORS;

import java.io.FileNotFoundException;
import java.io.IOException;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class MinimalConnectedSegmentsGenerator extends MinimalModelGenerator {

	public MinimalConnectedSegmentsGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Object region = serializer.createVertex(REGION);

		final Object sensor = serializer.createVertex(SENSOR);
		serializer.createEdge(SENSORS, region, sensor);

		final Object segment1 = serializer.createVertex(SEGMENT);
		final Object segment2 = serializer.createVertex(SEGMENT);
		final Object segment3 = serializer.createVertex(SEGMENT);
		final Object segment4 = serializer.createVertex(SEGMENT);
		final Object segment5 = serializer.createVertex(SEGMENT);
		final Object segment6 = serializer.createVertex(SEGMENT);

		serializer.createEdge(ELEMENTS, region, segment1);
		serializer.createEdge(ELEMENTS, region, segment2);
		serializer.createEdge(ELEMENTS, region, segment3);
		serializer.createEdge(ELEMENTS, region, segment4);
		serializer.createEdge(ELEMENTS, region, segment5);
		serializer.createEdge(ELEMENTS, region, segment6);

		serializer.createEdge(CONNECTS_TO, segment1, segment2);
		serializer.createEdge(CONNECTS_TO, segment2, segment3);
		serializer.createEdge(CONNECTS_TO, segment3, segment4);
		serializer.createEdge(CONNECTS_TO, segment4, segment5);
		serializer.createEdge(CONNECTS_TO, segment5, segment6);

		serializer.createEdge(MONITORED_BY, segment1, sensor);
		serializer.createEdge(MONITORED_BY, segment2, sensor);
		serializer.createEdge(MONITORED_BY, segment3, sensor);
		serializer.createEdge(MONITORED_BY, segment4, sensor);
		serializer.createEdge(MONITORED_BY, segment5, sensor);
		serializer.createEdge(MONITORED_BY, segment6, sensor);
	}

}
