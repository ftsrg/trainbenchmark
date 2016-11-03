package hu.bme.mit.trainbenchmark.generator;

import java.io.FileNotFoundException;
import java.io.IOException;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public abstract class ModelGenerator {

	protected final ModelSerializer<?> serializer;
	protected GeneratorConfig gc;

	public ModelGenerator(final ModelSerializer<?> serializer, final GeneratorConfig gc) {
		this.serializer = serializer;
		this.gc = gc;
	}

	public void generateModel() throws Exception {
		final StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Generating instance model, ");
		messageBuilder.append("generator: " + serializer.syntax() + ", ");
		// messageBuilder.append("scenario: " +
		// generatorConfig.getScenarioName() + ", ");
		// if (generatorConfig.getScenario() == MINIMAL) {
		// messageBuilder.append("query: " +
		// generatorConfig.getQueries().get(0));
		// } else {
		messageBuilder.append("size: " + gc.getConfigBase().getSize());
		// }
		messageBuilder.append("... ");
		System.out.print(messageBuilder.toString());
		serializer.initModel();
		constructModel();
		serializer.persistModel();
		System.out.println("Done.");
	}

	protected abstract void constructModel() throws FileNotFoundException, IOException;

}
