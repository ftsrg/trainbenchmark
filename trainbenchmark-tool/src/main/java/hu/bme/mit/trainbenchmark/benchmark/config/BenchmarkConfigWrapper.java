package hu.bme.mit.trainbenchmark.benchmark.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class BenchmarkConfigWrapper {

	protected BenchmarkConfigCore benchmarkConfig;
	
	protected BenchmarkConfigWrapper() {}
	
	public BenchmarkConfigWrapper(final BenchmarkConfigCore benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}
	
	public BenchmarkConfigCore getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public String getToolName() {
		return "Tool";
	}

	public String getWorkload() {
		return "Workload";
	}

	public String getToolNamePostfix() {
		return "";
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

	public static BenchmarkConfigWrapper fromFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final BenchmarkConfigWrapper benchmarkConfig = kryo.readObject(input, BenchmarkConfigWrapper.class);
			return benchmarkConfig;
		}
	}

	public static <T> T fromFile(final String path, final Class<T> class_) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final T benchmarkConfig = kryo.readObject(input, class_);
			return benchmarkConfig;
		}
	}
	
}
