package hu.bme.mit.trainbenchmark.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Kryo.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class AbstractConfig<TConfigBase extends AbstractConfigBase> {

	protected TConfigBase configBase;
	protected ExecutionConfig executionConfig;
	
	public AbstractConfig(final TConfigBase configBase, final ExecutionConfig executionConfig) {
		this.configBase = configBase;
		this.executionConfig = executionConfig;
	}

	public TConfigBase getConfigBase() {
		return configBase;
	}
	
	public ExecutionConfig getExecutionConfig() {
		return executionConfig;
	}

	/**
	 * Serialize the configuration to a file. This does not need to be redefined
	 * in subclasses (e.g. {EmfGeneratorConfig}), hence the method is declared
	 * as final.
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public final void saveToFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Output output = new Output(new FileOutputStream(path))) {
			kryo.writeObject(output, this);
		}
	}
	
	public static <T extends AbstractConfig<?>> T fromFile(final String path, final Class<T> clazz) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
	    try (final Input input = new Input(new FileInputStream(path))) {
			final T bc = kryo.readObject(input, clazz);
			return bc;
		}
	}

}
