package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import java.util.Comparator;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations.IQDModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class IQDBenchmarkScenario extends BenchmarkScenario<IQDCoreMatch, IQDCoreDriver, IQDBenchmarkConfigWrapper> {

	public IQDBenchmarkScenario(IQDCoreDriver driver, ModelOperationFactory<IQDCoreMatch, IQDCoreDriver> factory,
			Comparator<IQDCoreMatch> comparator, IQDBenchmarkConfigWrapper bcw) throws Exception {
		super(driver, factory, comparator, bcw);
	}

	public static IQDBenchmarkScenario create(IQDBenchmarkConfigWrapper config) throws Exception {
		final TransactionFactory input = new TransactionFactory(16);
		final IQDCoreDriver driver = new IQDCoreDriver(config, input);
		final IQDModelOperationFactory factory = new IQDModelOperationFactory(input);
		return new IQDBenchmarkScenario(driver, factory, IQDCoreMatchComparator.create(), config);

	}
}
