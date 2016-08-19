package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import java.util.Comparator;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations.IqdCoreModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class IqdCoreBenchmarkScenario extends BenchmarkScenario<IqdCoreMatch, IqdCoreDriver, IqdCoreBenchmarkConfigWrapper> {

	protected IqdCoreBenchmarkScenario(final IqdCoreDriverFactory driverFactory, final ModelOperationFactory<IqdCoreMatch, IqdCoreDriver> modelOperationFactory,
			final Comparator<IqdCoreMatch> comparator, IqdCoreBenchmarkConfigWrapper bcw) throws Exception {
		super(driverFactory, modelOperationFactory, comparator, bcw);
	}

	public static IqdCoreBenchmarkScenario create(final IqdCoreBenchmarkConfigWrapper bcw) throws Exception {
		final TransactionFactory transactionFactory = new TransactionFactory(bcw.getMessageSize());
		final IqdCoreDriverFactory driverFactory = new IqdCoreDriverFactory(bcw, transactionFactory);
		final IqdCoreModelOperationFactory modelOperationFactory = new IqdCoreModelOperationFactory(transactionFactory);
		return new IqdCoreBenchmarkScenario(driverFactory, modelOperationFactory, new IqdCoreMatchComparator(), bcw);
	}

}
