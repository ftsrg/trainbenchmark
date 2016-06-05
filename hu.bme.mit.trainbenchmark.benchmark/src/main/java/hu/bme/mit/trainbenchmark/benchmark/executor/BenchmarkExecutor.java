package hu.bme.mit.trainbenchmark.benchmark.executor;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class BenchmarkExecutor<TPatternMatch, TDriver extends Driver<?>> {

	protected final Random random = new Random(0);
	protected final TDriver driver;
	protected final ModelOperationFactory<TPatternMatch, TDriver> factory;
	protected final Comparator<TPatternMatch> comparator;
	protected Collection<QueryShuffleTransformation<? extends TPatternMatch, TDriver>> qsts = new LinkedList<>();

	public BenchmarkExecutor(final TDriver driver, final ModelOperationFactory<TPatternMatch, TDriver> factory,
			final Comparator<TPatternMatch> comparator) {
		this.driver = driver;
		this.factory = factory;
		this.comparator = comparator;
	}

	public void read() throws Exception {
		driver.read("../models/railway-repair-1.xmi");
	}

	public void initializeOperations(final Collection<RailwayOperation> railwayOperations) throws Exception {
		for (final RailwayOperation railwayOperation : railwayOperations) {
			final ModelOperation<? extends TPatternMatch, TDriver> operation = factory.createOperation(railwayOperation, driver);
			final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst = QueryShuffleTransformation.of(operation, comparator, random);
			qsts.add(qst);
		}
	}

	public void query() throws Exception {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			final Collection<?> matches = qst.evaluateQuery();
			System.out.println(matches.size());
		}
	}

	public void shuffle() {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			qst.shuffle(10);
		}
	}

	public void transform() throws Exception {
		for (final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst : qsts) {
			qst.transform();
		}
	}

}
