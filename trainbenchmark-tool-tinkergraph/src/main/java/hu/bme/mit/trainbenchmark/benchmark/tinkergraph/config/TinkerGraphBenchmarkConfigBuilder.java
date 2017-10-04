package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config;

import com.google.common.base.Preconditions;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;

public class TinkerGraphBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<TinkerGraphBenchmarkConfig, TinkerGraphBenchmarkConfigBuilder> {

	protected TinkerGraphEngine engine;

	public TinkerGraphBenchmarkConfigBuilder setEngine(final TinkerGraphEngine engine) {
		this.engine = engine;
		return this;
	}

	@Override
	public TinkerGraphBenchmarkConfig createConfig() {
		checkNotNulls();
		return new TinkerGraphBenchmarkConfig(configBase, engine);
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(engine);
	}

}
