package hu.bme.mit.trainbenchmark.benchmark.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class BenchmarkConfigWrapper {

	protected BenchmarkConfig benchmarkConfig;
	
	protected BenchmarkConfigWrapper() {}
	
	public BenchmarkConfigWrapper(final BenchmarkConfig benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}
	
	public BenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public String getToolNamePostfix() {
		return "";
	}

	public void saveToFile(final String path) throws FileNotFoundException {
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
	
}
