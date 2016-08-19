package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

public class BenchmarkBundle<TPatternMatch, TDriver extends Driver, TBenchmarkConfigWrapper extends BenchmarkConfigWrapper> {

	protected final Random random = new Random(TrainBenchmarkConstants.RANDOM_SEED);
	protected final TDriver driver;
	protected final ModelOperationFactory<TPatternMatch, TDriver> factory;
	protected final Comparator<TPatternMatch> comparator;
	protected final TBenchmarkConfigWrapper bcw;
	protected final BenchmarkResult benchmarkResults;

	protected Collection<QueryShuffleTransformation<? extends TPatternMatch, TDriver>> qsts = new LinkedList<>();

	public BenchmarkBundle(final DriverFactory<TDriver> driverFactory, final ModelOperationFactory<TPatternMatch, TDriver> factory,
			final Comparator<TPatternMatch> comparator, final TBenchmarkConfigWrapper bcw,
			final BenchmarkResult benchmarkResults) throws Exception {
		this.driver = driverFactory.createInstance();
		this.factory = factory;
		this.comparator = comparator;
		this.bcw = bcw;
		this.benchmarkResults = benchmarkResults;
	}

	public void initializeDriver() throws Exception {
		
		driver.initialize();
	}

	public void read() throws Exception {
		final String modelPath = bcw.getBenchmarkConfig().getModelPath() + driver.getPostfix();
		driver.read(modelPath);
	}

	public void initializeOperations() throws Exception {
		for (final RailwayOperation railwayOperation : bcw.getBenchmarkConfig().getRailwayOperations()) {

			final Optional<String> workspacePath = Optional.of(bcw.getBenchmarkConfig().getWorkspaceDir());

			final ModelOperation<? extends TPatternMatch, TDriver> operation = factory.createOperation(railwayOperation,
					workspacePath, driver);
			final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst = QueryShuffleTransformation
					.of(operation, comparator, random);
			qsts.add(qst);
		}
	}

	public void query() throws Exception {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			final Collection<?> matches = qst.evaluateQuery();
			benchmarkResults.registerMatches(qst.getQuery().getQuery(), matches.size());
		}
	}

	public void shuffle() {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			if (qst.isTransformation()) {
				qst.shuffle(10);
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

}
