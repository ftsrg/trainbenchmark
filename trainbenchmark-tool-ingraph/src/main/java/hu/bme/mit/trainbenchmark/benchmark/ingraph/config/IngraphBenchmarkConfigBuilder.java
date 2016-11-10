package hu.bme.mit.trainbenchmark.benchmark.ingraph.config;

import com.google.api.client.util.Preconditions;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class IngraphBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<IngraphBenchmarkConfigBuilder, IngraphBenchmarkConfig> {

	protected Integer messageSize;
	protected String queryVariant;

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(messageSize);
	}

	public IngraphBenchmarkConfigBuilder setMessageSize(Integer messageSize) {
		this.messageSize = messageSize;
		return this;
	}

	public IngraphBenchmarkConfigBuilder setQueryVariant(String queryVariant) {
		this.queryVariant = queryVariant;
		return this;
	}

	@Override
	public IngraphBenchmarkConfig createBenchmarkConfig() {
		checkNotNulls();
		return new IngraphBenchmarkConfig(configBase, messageSize, queryVariant);
	}

}
