package hu.bme.mit.trainbenchmark.benchmark.rdf.config;

import com.google.common.base.Preconditions;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public abstract class RdfBenchmarkConfigBuilder<T extends RdfBenchmarkConfig, B extends RdfBenchmarkConfigBuilder<T, ?>>
		extends BenchmarkConfigBuilder<T, B> {

	protected Boolean inferencing;
	protected RdfFormat format;

	public B setInferencing(final boolean inferencing) {
		this.inferencing = inferencing;
		return (B) this;
	}

	public B setFormat(RdfFormat format) {
		this.format = format;
		return (B) this;
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(inferencing);
		Preconditions.checkNotNull(format);
	}

}
