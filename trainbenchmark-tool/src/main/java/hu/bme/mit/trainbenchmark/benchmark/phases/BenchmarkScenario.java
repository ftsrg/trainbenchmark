package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResults;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;

public class BenchmarkScenario<TPatternMatch, TDriver extends Driver<?>, TBenchmarkConfigWrapper extends BenchmarkConfigWrapper> {

	protected final PhaseExecutor phaseExecutor = new PhaseExecutor();
	protected final BenchmarkExecutor<TPatternMatch, TDriver, TBenchmarkConfigWrapper> benchmarkExecutor;
	protected final BenchmarkResults benchmarkResults = new BenchmarkResults();
	protected final TBenchmarkConfigWrapper bcw;

	public BenchmarkScenario(final TDriver driver, final ModelOperationFactory<TPatternMatch, TDriver> factory,
			final Comparator<TPatternMatch> comparator, final TBenchmarkConfigWrapper bcw) throws Exception {
		this.bcw = bcw;
		this.benchmarkExecutor = new BenchmarkExecutor<>(driver, factory, comparator, bcw, benchmarkResults);
	}

	public void runBenchmark() throws Exception {
		final InitializeOperationsPhase initializeOperationsPhase = new InitializeOperationsPhase(benchmarkExecutor);
		final ReadPhase readPhase = new ReadPhase(benchmarkExecutor);
		final QueryPhase queryPhase = new QueryPhase(benchmarkExecutor);
		final TransformationPhase transformationPhase = new TransformationPhase(benchmarkExecutor);

		phaseExecutor.execute(initializeOperationsPhase);
		phaseExecutor.execute(readPhase);
		phaseExecutor.execute(queryPhase);

		// transformation-recheck loops
		for (int i = 0; i < bcw.getBenchmarkConfig().getQueryTransformationCount(); i++) {
			phaseExecutor.execute(transformationPhase);
			phaseExecutor.execute(queryPhase);
		}
		
		System.out.println(benchmarkResults);
	}

}
