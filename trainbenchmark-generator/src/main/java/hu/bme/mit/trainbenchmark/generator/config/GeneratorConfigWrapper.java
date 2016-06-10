package hu.bme.mit.trainbenchmark.generator.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class GeneratorConfigWrapper {

	protected GeneratorConfig generatorConfig;
	
	protected GeneratorConfigWrapper() {}

	public GeneratorConfigWrapper(final GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
	}
	
	public GeneratorConfig getGeneratorConfig() {
		return generatorConfig;
	}
	
	/**
	 * Serialize the configuration to a file. This does not need to be redefined in the subclasses (e.g. {Neo4jBenchmarkConfigWrapper}).
	 * @param path
	 * @throws FileNotFoundException
	 */
	public final void saveToFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Output output = new Output(new FileOutputStream(path))) {
			kryo.writeObject(output, this);
		}
	}

	public static GeneratorConfigWrapper fromFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final GeneratorConfigWrapper generatorConfigWrapper = kryo.readObject(input, GeneratorConfigWrapper.class);
			return generatorConfigWrapper;
		}
	}
	
}
