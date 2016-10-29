package hu.bme.mit.trainbenchmark.generator.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;

public abstract class GeneratorConfig extends AbstractConfig<GeneratorConfigBase> {

	protected GeneratorConfig() {
	}

	public GeneratorConfig(final GeneratorConfigBase configBase) {
		this.configBase = configBase;
	}

	/**
	 * @return The name of the project to be executed. Example: "emf"
	 */
	public abstract String getProjectName();

	public GeneratorConfigBase getGeneratorConfig() {
		return configBase;
	}

	public static <T extends GeneratorConfig> T fromFile(final String path, final Class<T> clazz) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final T gc = kryo.readObject(input, clazz);
			return gc;
		}
	}

}
