package hu.bme.mit.trainbenchmark.benchmark.executor;

import java.util.Comparator;
import java.util.Random;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class BenchmarkExecutor<TDriver extends Driver<?>, TPatternMatch> {

	protected final Random random = new Random(0);
	protected final TDriver driver;
	protected final ModelOperationFactory<TPatternMatch, TDriver> factory;
	protected final Comparator<TPatternMatch> comparator;

	public BenchmarkExecutor(final TDriver driver, final ModelOperationFactory<TPatternMatch, TDriver> factory,
			Comparator<TPatternMatch> comparator) {
		this.driver = driver;
		this.factory = factory;
		this.comparator = comparator;
	}
	
	public void executeGeneric() throws Exception {
		driver.read("../models/railway-repair-1.xmi");

		final ModelOperation<? extends TPatternMatch, TDriver> operation = factory.createOperation(RailwayOperation.POSLENGTH_REPAIR,
				driver);

		final QueryShuffleTransformation<? extends TPatternMatch, TDriver> qst = QueryShuffleTransformation.of(operation, comparator,
				random);
		qst.evaluateQuery();
		qst.shuffle(10);
		qst.transform();
	}
}
