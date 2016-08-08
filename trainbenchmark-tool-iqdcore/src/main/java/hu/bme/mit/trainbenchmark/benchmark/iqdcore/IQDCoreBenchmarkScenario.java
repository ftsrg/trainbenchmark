package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import java.util.Comparator;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDCoreBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations.IQDCoreModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class IQDCoreBenchmarkScenario extends BenchmarkScenario<IQDCoreMatch, IQDCoreDriver, IQDCoreBenchmarkConfigWrapper> {

	public IQDCoreBenchmarkScenario(IQDCoreDriver driver, ModelOperationFactory<IQDCoreMatch, IQDCoreDriver> factory,
			Comparator<IQDCoreMatch> comparator, IQDCoreBenchmarkConfigWrapper bcw) throws Exception {
		super(driver, factory, comparator, bcw);
	}

	public static IQDCoreBenchmarkScenario create(IQDCoreBenchmarkConfigWrapper config) throws Exception {
		final TransactionFactory input = new TransactionFactory(16);
		final IQDCoreDriver driver = new IQDCoreDriver(config, input);
		final IQDCoreModelOperationFactory factory = new IQDCoreModelOperationFactory(input);
		return new IQDCoreBenchmarkScenario(driver, factory, IQDCoreMatchComparator.create(), config);

	}
}
