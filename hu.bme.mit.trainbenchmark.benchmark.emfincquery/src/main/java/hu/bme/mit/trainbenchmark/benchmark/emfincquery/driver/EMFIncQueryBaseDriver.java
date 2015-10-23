package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFIncQueryBaseDriver<TMatch extends BasePatternMatch, TBenchmarkConfig extends BenchmarkConfig>
		extends EMFDriver<TBenchmarkConfig> {

	protected AdvancedIncQueryEngine engine;

	public EMFIncQueryBaseDriver(final TBenchmarkConfig benchmarkConfig) {
		super(benchmarkConfig);
	}

	public AdvancedIncQueryEngine getEngine() {
		return engine;
	}

}
