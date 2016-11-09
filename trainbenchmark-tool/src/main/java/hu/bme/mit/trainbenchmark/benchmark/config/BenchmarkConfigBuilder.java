package hu.bme.mit.trainbenchmark.benchmark.config;

import com.google.api.client.util.Preconditions;

/**
 * This class uses the Curiously-Recurring Generic Pattern,
 * see http://www.artima.com/weblogs/viewpost.jsp?thread=133275 for details.
 */
public abstract class BenchmarkConfigBuilder<B extends BenchmarkConfigBuilder<?, ?>, T extends BenchmarkConfig> {

	protected BenchmarkConfigBase configBase;

	@SuppressWarnings("unchecked")
	public B setConfigBase(final BenchmarkConfigBase configBase) {
		this.configBase = configBase;
		return (B) this;
	}

	public abstract T createBenchmarkConfig();

	public void checkNotNulls() {
		Preconditions.checkNotNull(configBase);
	}

}
