package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.GATHERS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;

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
		final Object sensor = serializer.createVertex(SENSOR);
		final Object segment1 = serializer.createVertex(SEGMENT);
		final Object segment2 = serializer.createVertex(SEGMENT);
		final Object segment3 = serializer.createVertex(SEGMENT);
		final Object segment4 = serializer.createVertex(SEGMENT);
		final Object segment5 = serializer.createVertex(SEGMENT);
		final Object segment6 = serializer.createVertex(SEGMENT);

		serializer.createEdge(CONNECTSTO, segment1, segment2);
		serializer.createEdge(CONNECTSTO, segment2, segment3);
		serializer.createEdge(CONNECTSTO, segment3, segment4);
		serializer.createEdge(CONNECTSTO, segment4, segment5);
		serializer.createEdge(CONNECTSTO, segment5, segment6);

		serializer.createEdge(SENSOR_EDGE, segment1, sensor);
		serializer.createEdge(SENSOR_EDGE, segment2, sensor);
		serializer.createEdge(SENSOR_EDGE, segment3, sensor);
		serializer.createEdge(SENSOR_EDGE, segment4, sensor);
		serializer.createEdge(SENSOR_EDGE, segment5, sensor);
		serializer.createEdge(SENSOR_EDGE, segment6, sensor);

		// this is required by the EMF serializer to fix the containment hierarchy
		serializer.createEdge(GATHERS, null, sensor);
	}

}
