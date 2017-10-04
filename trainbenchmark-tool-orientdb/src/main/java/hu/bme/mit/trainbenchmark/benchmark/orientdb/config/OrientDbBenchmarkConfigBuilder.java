
package hu.bme.mit.trainbenchmark.benchmark.orientdb.config;

import com.google.common.base.Preconditions;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphEngine;

public class OrientDbBenchmarkConfigBuilder
		extends BenchmarkConfigBuilder<OrientDbBenchmarkConfig, OrientDbBenchmarkConfigBuilder> {

	protected TinkerGraphEngine engine;

	public OrientDbBenchmarkConfigBuilder setEngine(final TinkerGraphEngine engine) {
		this.engine = engine;
		return this;
	}

	@Override
	public OrientDbBenchmarkConfig createConfig() {
		checkNotNulls();
		return new OrientDbBenchmarkConfig(configBase, engine);
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(engine);
	}

}
