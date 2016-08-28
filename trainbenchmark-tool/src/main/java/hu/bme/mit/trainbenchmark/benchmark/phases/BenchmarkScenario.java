package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkBundle;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;

public class BenchmarkScenario<TPatternMatch, TDriver extends Driver, TBenchmarkConfigWrapper extends BenchmarkConfigWrapper> {

	protected final PhaseExecutor phaseExecutor = new PhaseExecutor();

	protected final DriverFactory<TDriver> driverFactory;
	protected final ModelOperationFactory<TPatternMatch, TDriver> modelOperationFactory;
	protected final Comparator<TPatternMatch> comparator;
	protected final TBenchmarkConfigWrapper bcw;
	protected final BenchmarkResult benchmarkResult;

	public BenchmarkScenario(final DriverFactory<TDriver> driverFactory, final ModelOperationFactory<TPatternMatch, TDriver> modelOperationFactory,
			final Comparator<TPatternMatch> comparator, final TBenchmarkConfigWrapper bcw) throws Exception {
		this.driverFactory = driverFactory;
		this.modelOperationFactory = modelOperationFactory;
		this.comparator = comparator;
		this.bcw = bcw;

		final BenchmarkConfigCore bcc = bcw.getBenchmarkConfigCore();
		this.benchmarkResult = new BenchmarkResult(bcw.getToolName(), bcc.getWorkload(), bcc.getWorkspaceDir(), bcc.getModelFilename(), bcw.getDescription());
	}

	public BenchmarkResult performBenchmark() throws Exception {
		for (int i = 0; i < bcw.getBenchmarkConfigCore().getRuns(); i++) {
			performRun();
		}

		benchmarkResult.serialize();
		return benchmarkResult;
	}

	protected void performRun() throws Exception {
		benchmarkResult.nextRun();

		final BenchmarkBundle<TPatternMatch, TDriver, TBenchmarkConfigWrapper> benchmarkBundle = new BenchmarkBundle<>(driverFactory, modelOperationFactory,
				comparator, bcw, benchmarkResult);

		final InitializeOperationsPhase initializeOperationsPhase = new InitializeOperationsPhase(benchmarkBundle);
		final ReadPhase readPhase = new ReadPhase(benchmarkBundle);
		final QueryPhase queryPhase = new QueryPhase(benchmarkBundle);
		final TransformationPhase transformationPhase = new TransformationPhase(benchmarkBundle);
		final CleanupPhase cleanupPhase = new CleanupPhase(benchmarkBundle);

		try {
			phaseExecutor.execute(initializeOperationsPhase);

			// read
			final long readTime = phaseExecutor.execute(readPhase);
			benchmarkResult.registerReadTime(readTime);

			// check
			final long queryTime = phaseExecutor.execute(queryPhase);
			benchmarkResult.registerQueryTime(queryTime);

			// transformation-recheck iterations
			for (int i = 0; i < bcw.getBenchmarkConfigCore().getQueryTransformationCount(); i++) {
				final long transformationTime = phaseExecutor.execute(transformationPhase);
				benchmarkResult.registerTransformationTime(transformationTime);

				final long recheckTime = phaseExecutor.execute(queryPhase);
				benchmarkResult.registerQueryTime(recheckTime);
			}
		} finally {
			phaseExecutor.execute(cleanupPhase);
		}
	}

}
