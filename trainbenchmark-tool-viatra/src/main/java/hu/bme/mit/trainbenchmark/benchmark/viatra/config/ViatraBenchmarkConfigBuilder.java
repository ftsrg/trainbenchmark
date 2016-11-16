package hu.bme.mit.trainbenchmark.benchmark.viatra.config;

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class ViatraBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<ViatraBenchmarkConfig, ViatraBenchmarkConfigBuilder> {

	private ViatraBackend backend;

	public ViatraBenchmarkConfigBuilder setBackend(final ViatraBackend backend) {
		this.backend = backend;
		return this;
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(backend);
	}

	@Override
	public ViatraBenchmarkConfig createConfig() {
		checkNotNulls();
		return new ViatraBenchmarkConfig(configBase, backend);
	}
}
