package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import java.util.Comparator;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations.IqdCoreModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class IqdCoreBenchmarkScenario extends BenchmarkScenario<IqdCoreMatch, IqdCoreDriver, IqdCoreBenchmarkConfig> {

	protected IqdCoreBenchmarkScenario(final IqdCoreDriverFactory driverFactory, final ModelOperationFactory<IqdCoreMatch, IqdCoreDriver> modelOperationFactory,
			final Comparator<IqdCoreMatch> comparator, IqdCoreBenchmarkConfig bc) throws Exception {
		super(driverFactory, modelOperationFactory, comparator, bc);
	}

	public static IqdCoreBenchmarkScenario create(final IqdCoreBenchmarkConfig bc) throws Exception {
		final TransactionFactory transactionFactory = new TransactionFactory(bc.getMessageSize());
		final IqdCoreDriverFactory driverFactory = new IqdCoreDriverFactory(bc, transactionFactory);
		final IqdCoreModelOperationFactory modelOperationFactory = new IqdCoreModelOperationFactory(transactionFactory);
		return new IqdCoreBenchmarkScenario(driverFactory, modelOperationFactory, new IqdCoreMatchComparator(), bc);
	}

}
