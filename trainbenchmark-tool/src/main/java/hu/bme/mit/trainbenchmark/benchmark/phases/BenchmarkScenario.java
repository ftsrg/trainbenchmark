package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkBundle;
import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;

public class BenchmarkScenario<TPatternMatch, TDriver extends Driver<?>, TBenchmarkConfigWrapper extends BenchmarkConfigWrapper> {

	protected final PhaseExecutor phaseExecutor = new PhaseExecutor();

	protected final TDriver driver;
	protected final ModelOperationFactory<TPatternMatch, TDriver> factory;
	protected final Comparator<TPatternMatch> comparator;
	protected final TBenchmarkConfigWrapper bcw;
	protected final BenchmarkResult benchmarkResult;

	public BenchmarkScenario(final TDriver driver, final ModelOperationFactory<TPatternMatch, TDriver> factory,
			final Comparator<TPatternMatch> comparator, final TBenchmarkConfigWrapper bcw) throws Exception {
		this.driver = driver;
		this.factory = factory;
		this.comparator = comparator;
		this.bcw = bcw;
		this.benchmarkResult = new BenchmarkResult(bcw.getToolName(), bcw.getBenchmarkConfig().getWorkload());
	}

	public BenchmarkResult performBenchmark() throws Exception {
		for (int i = 0; i < bcw.getBenchmarkConfig().getRuns(); i++) {
			performRun();
		}
		return benchmarkResult;
	}

	protected void performRun() throws Exception {
		benchmarkResult.nextRun();

		final BenchmarkBundle<TPatternMatch, TDriver, TBenchmarkConfigWrapper> benchmarkBundle = new BenchmarkBundle<>(
				driver, factory, comparator, bcw, benchmarkResult);

		final InitializeOperationsPhase initializeOperationsPhase = new InitializeOperationsPhase(benchmarkBundle);
		final ReadPhase readPhase = new ReadPhase(benchmarkBundle);
		final QueryPhase queryPhase = new QueryPhase(benchmarkBundle);
		final TransformationPhase transformationPhase = new TransformationPhase(benchmarkBundle);
		final CleanupPhase cleanupPhase = new CleanupPhase(benchmarkBundle);

		phaseExecutor.execute(initializeOperationsPhase);

		// read
		final long readTime = phaseExecutor.execute(readPhase);
		benchmarkResult.registerReadTime(readTime);

		// check
		final long queryTime = phaseExecutor.execute(queryPhase);
		benchmarkResult.registerQueryTime(queryTime);

		// transformation-recheck iterations
		for (int i = 0; i < bcw.getBenchmarkConfig().getQueryTransformationCount(); i++) {
			final long transformationTime = phaseExecutor.execute(transformationPhase);
			benchmarkResult.registerTransformationTime(transformationTime);

			final long recheckTime = phaseExecutor.execute(queryPhase);
			benchmarkResult.registerQueryTime(recheckTime);
		}
		
		phaseExecutor.execute(cleanupPhase);
	}

}
