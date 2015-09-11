package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_EDGE;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		final Object swP = serializer.createVertex(SWITCHPOSITION);
		final Object sw = serializer.createVertex(SWITCH);

		serializer.createEdge(FOLLOWS, route, swP);
		serializer.createEdge(SWITCH_EDGE, swP, sw);
		serializer.createEdge(SENSOR_EDGE, sw, sensor);
	}

}
