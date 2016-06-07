package hu.bme.mit.trainbenchmark.benchmark.config;

public class BenchmarkConfigWrapper {

	protected BenchmarkConfig benchmarkConfig;
	
	public BenchmarkConfigWrapper(final BenchmarkConfig benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}
	
	public BenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public String getToolNamePostfix() {
		return "";
	}

}
