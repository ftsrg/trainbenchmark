package hu.bme.mit.trainbenchmark.benchmark.rdf.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public abstract class RdfBenchmarkConfigBuilder<B extends RdfBenchmarkConfigBuilder<?, ?>, T extends RdfBenchmarkConfig>
		extends BenchmarkConfigBuilder<B, T> {

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
