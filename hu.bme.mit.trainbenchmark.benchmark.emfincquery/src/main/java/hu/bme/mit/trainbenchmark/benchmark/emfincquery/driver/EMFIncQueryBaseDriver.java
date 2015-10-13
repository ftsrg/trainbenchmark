package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import java.io.IOException;

import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFIncQueryBaseDriver<TMatch extends BasePatternMatch, TBenchmarkConfig extends BenchmarkConfig>
		extends EMFDriver<TBenchmarkConfig> {

	protected EMFIncQueryChecker<TMatch> checker;
	protected AdvancedIncQueryEngine engine;

	public EMFIncQueryBaseDriver(final TBenchmarkConfig benchmarkConfig) {
		super(benchmarkConfig);
	}

	public void registerChecker(final EMFIncQueryChecker<TMatch> checker) throws IOException {
		this.checker = checker;
	}

	public AdvancedIncQueryEngine getEngine() {
		return engine;
	}

}
