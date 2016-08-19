package hu.bme.mit.trainbenchmark.benchmark.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public abstract class BenchmarkConfigWrapper {

	protected BenchmarkConfigCore bcc;

	protected BenchmarkConfigWrapper() {
	}

	public BenchmarkConfigWrapper(final BenchmarkConfigCore bcc) {
		this.bcc = bcc;
	}

	public BenchmarkConfigCore getBenchmarkConfigCore() {
		return bcc;
	}

	/**
	 * @return The name of the tools for storing the benchmark results. Example: "RDF4J (No Inferencing)"
	 */
	public abstract String getToolName();

	/**
	 * @return The name of the project to be executed. Example: "sesame"
	 */
	public abstract String getProjectName();

	public String getDescription() {
		return "";
	}
	
	/**
	 * Serialize the configuration to a file. This does not need to be redefined in the subclasses (e.g.
	 * {Neo4jBenchmarkConfigWrapper}).
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

	public static <T extends BenchmarkConfigWrapper> T fromFile(final String path, final Class<T> clazz) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final T bcw = kryo.readObject(input, clazz);
			return bcw;
		}
	}
	
}
