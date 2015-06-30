package token;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import eu.mondo.sam.core.DataToken;

public class TrainBenchmarkDataToken implements DataToken {

	private AbstractBenchmarkCase<?, ?> benchmarkCase;

	private BenchmarkConfig config;

	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}

	public AbstractBenchmarkCase<?, ?> getBenchmarkCase() {
		return benchmarkCase;
	}

	public void setBenchmarkCase(AbstractBenchmarkCase<?, ?> benchmarkCase) {
		this.benchmarkCase = benchmarkCase;
	}

	public BenchmarkConfig getConfig() {
		return config;
	}

	public void setConfig(BenchmarkConfig config) {
		this.config = config;
	}

}
