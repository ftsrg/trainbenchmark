package hu.bme.mit.trainbenchmark.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

public class AbstractConfig<TConfigBase extends AbstractConfigBase> {

	protected TConfigBase configBase;

	/**
	 * Serialize the configuration to a file. This does not need to be redefined in subclasses (e.g.
	 * {EmfGeneratorConfig}), hence the method is declared as final.
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
}
