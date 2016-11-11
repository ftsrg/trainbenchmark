package hu.bme.mit.trainbenchmark.benchmark.rdf.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public abstract class RdfBenchmarkConfigBuilder<T extends RdfBenchmarkConfig, B extends RdfBenchmarkConfigBuilder<T, ?>>
		extends BenchmarkConfigBuilder<T, B> {

	protected Boolean inferencing;

	@SuppressWarnings("unchecked")
	public B setInferencing(final boolean inferencing) {
		this.inferencing = inferencing;
		return (B) this;
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(inferencing);
	}

}
