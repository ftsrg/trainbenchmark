package hu.bme.mit.trainbenchmark.generator.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public abstract class GeneratorConfigWrapper {

	protected GeneratorConfigCore generatorConfig;
	
	protected GeneratorConfigWrapper() {}

	public GeneratorConfigWrapper(final GeneratorConfigCore generatorConfig) {
		this.generatorConfig = generatorConfig;
	}
	
	/**
	 * @return The name of the project to be executed. Example: "emf"
	 */
	public abstract String getProjectName();
		
	public GeneratorConfigCore getGeneratorConfig() {
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

	public static <T extends GeneratorConfigWrapper> T fromFile(final String path, final Class<T> clazz) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final T generatorConfigWrapper = kryo.readObject(input, clazz);
			return generatorConfigWrapper;
		}
	}
	
}
