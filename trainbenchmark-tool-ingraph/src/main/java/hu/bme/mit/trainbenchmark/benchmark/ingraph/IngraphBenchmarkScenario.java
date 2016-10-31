package hu.bme.mit.trainbenchmark.benchmark.ingraph;

import java.util.Comparator;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.config.IngraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.operations.IngraphModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class IngraphBenchmarkScenario extends BenchmarkScenario<IngraphMatch, IngraphDriver, IngraphBenchmarkConfig> {

	protected IngraphBenchmarkScenario(final IngraphDriverFactory driverFactory, final ModelOperationFactory<IngraphMatch, IngraphDriver> modelOperationFactory,
			final Comparator<IngraphMatch> comparator, final IngraphBenchmarkConfig bc) throws Exception {
		super(driverFactory, modelOperationFactory, comparator, bc);
	}

	public static IngraphBenchmarkScenario create(final IngraphBenchmarkConfig bc) throws Exception {
		final TransactionFactory transactionFactory = new TransactionFactory(bc.getMessageSize());
		final IngraphDriverFactory driverFactory = new IngraphDriverFactory(bc, transactionFactory);
		final IngraphModelOperationFactory modelOperationFactory = new IngraphModelOperationFactory(transactionFactory);
		return new IngraphBenchmarkScenario(driverFactory, modelOperationFactory, new IngraphMatchComparator(), bc);
	}

}
