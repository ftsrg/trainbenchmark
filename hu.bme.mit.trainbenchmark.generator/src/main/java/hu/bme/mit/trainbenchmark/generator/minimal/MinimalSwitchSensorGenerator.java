package hu.bme.mit.trainbenchmark.generator.minimal;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;

import java.io.FileNotFoundException;
import java.io.IOException;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class MinimalSwitchSensorGenerator extends MinimalModelGenerator {

	public MinimalSwitchSensorGenerator(final ModelSerializer serializer, final GeneratorConfig generatorConfig) {
		super(serializer, generatorConfig);
	}

	@Override
	protected void buildPatternModel() throws FileNotFoundException, IOException {
		final Object sw = serializer.createVertex(SWITCH);

		// this is required by the EMF serializer to fix the containment hierarchy
		serializer.createEdge(MONITORED_BY, sw, null);
	}

}
