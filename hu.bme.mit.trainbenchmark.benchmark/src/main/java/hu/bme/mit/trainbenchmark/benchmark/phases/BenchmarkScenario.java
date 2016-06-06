package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;

public class BenchmarkScenario<TPatternMatch, TDriver extends Driver<?>, TBenchmarkConfig extends BenchmarkConfig> {

	protected final PhaseExecutor phaseExecutor = new PhaseExecutor();
	protected final BenchmarkExecutor<TPatternMatch, TDriver, TBenchmarkConfig> executor;

	public BenchmarkScenario(final TDriver driver, final ModelOperationFactory<TPatternMatch, TDriver> factory, final Comparator<TPatternMatch> comparator, final TBenchmarkConfig benchmarkConfig) throws Exception {
		executor = new BenchmarkExecutor<>(driver, factory, comparator, benchmarkConfig);

	}

	public void runBenchmark() throws Exception {
		final ReadPhase readPhase = new ReadPhase(executor);
		final InitializeOperationsPhase initializeOperationsPhase = new InitializeOperationsPhase(executor);
		final QueryPhase queryPhase = new QueryPhase(executor);
		final TransformationPhase transformationPhase = new TransformationPhase(executor);

		phaseExecutor.execute(readPhase);
		phaseExecutor.execute(initializeOperationsPhase);
		phaseExecutor.execute(queryPhase);

		// transformation-recheck loops
		for (int i = 0; i < 10; i++) {
			phaseExecutor.execute(transformationPhase);
			phaseExecutor.execute(queryPhase);
		}
	}

}