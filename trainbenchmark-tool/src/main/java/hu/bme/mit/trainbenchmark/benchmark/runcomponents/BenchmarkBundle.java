package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.ExecutionPhase;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BenchmarkBundle<TPatternMatch, TDriver extends Driver, TBenchmarkConfig extends BenchmarkConfig> {

	protected final Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
	protected final TDriver driver;
	protected final ModelOperationFactory<TPatternMatch, TDriver> factory;
	protected final Comparator<TPatternMatch> comparator;
	protected final TBenchmarkConfig bc;
	protected final BenchmarkResult benchmarkResults;

	protected Collection<QueryShuffleTransformation<? extends TPatternMatch, TDriver>> qsts = new LinkedList<>();

	public BenchmarkBundle(final DriverFactory<TDriver> driverFactory,
			final ModelOperationFactory<TPatternMatch, TDriver> factory, final Comparator<TPatternMatch> comparator,
			final TBenchmarkConfig bc, final BenchmarkResult benchmarkResults) throws Exception {
		this.driver = driverFactory.createInstance();
		this.factory = factory;
		this.comparator = comparator;
		this.bc = bc;
		this.benchmarkResults = benchmarkResults;
	}

	public void initializeDriver() throws Exception {
		driver.initialize();
	}

	public void read() throws Exception {
		final String modelPath = bc.getConfigBase().getModelPath() + driver.getPostfix();
		driver.read(modelPath);
	}

	public void initializeOperations() throws Exception {
		for (final RailwayOperation railwayOperation : bc.getConfigBase().getOperations()) {
			final String workspaceDir = bc.getConfigBase().getWorkspaceDir();

			final ModelOperation<? extends TPatternMatch, TDriver> modelOperation = factory
					.createOperation(railwayOperation, workspaceDir, driver);
			final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst = QueryShuffleTransformation
					.of(modelOperation, comparator, random, driver);
			qsts.add(qst);
		}
	}

	public void query() throws Exception {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			if (qst.getQueryExecutionPhase() == ExecutionPhase.CHECK) {
				final Collection<?> matches = qst.query();
				benchmarkResults.registerMatches(qst.getQuery().getQuery(), matches.size());
			}
		}
	}

	public void shuffle() throws Exception {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			if (qst.getQueryExecutionPhase() == ExecutionPhase.TRANSFORMATION) {
				qst.query();
			}

			if (qst.isTransformation()) {
				final int changeSetSize = determineChangeSet(qst,
						bc.getConfigBase().getTransformationChangeSetStrategy(),
						bc.getConfigBase().getTransformationConstant());
				List<? extends TPatternMatch> matches = qst.shuffle(changeSetSize);
			}
		}
	}

	public void transform() throws Exception {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			if (qst.isTransformation()) {
				qst.transform();
			}
		}
	}

	public void cleanup() throws Exception {
		driver.destroy();
	}

	public int determineChangeSet(final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst,
			final TransformationChangeSetStrategy transformationChangeSetStrategy, final int transformationConstant) {
		switch (transformationChangeSetStrategy) {
		case FIXED:
			return transformationConstant;
		case PROPORTIONAL:
			return qst.getMatches().size() * transformationConstant / 100;
		default:
			throw new UnsupportedOperationException(
					"Transformation change set strategy " + transformationChangeSetStrategy + " not supported.");
		}
	}

}
