package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import java.util.Comparator;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations.IqdCoreModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

public class IqdCoreBenchmarkScenario extends BenchmarkScenario<IqdCoreMatch, IqdCoreDriver, IqdCoreBenchmarkConfigWrapper> {

	public IqdCoreBenchmarkScenario(IqdCoreDriver driver, ModelOperationFactory<IqdCoreMatch, IqdCoreDriver> factory,
			Comparator<IqdCoreMatch> comparator, IqdCoreBenchmarkConfigWrapper bcw) throws Exception {
		super(driver, factory, comparator, bcw);
	}

	public static IqdCoreBenchmarkScenario create(IqdCoreBenchmarkConfigWrapper config) throws Exception {
		final TransactionFactory input = new TransactionFactory(16);
		final IqdCoreDriver driver = new IqdCoreDriver(config, input);
		final IqdCoreModelOperationFactory factory = new IqdCoreModelOperationFactory(input);
		return new IqdCoreBenchmarkScenario(driver, factory, IqdCoreMatchComparator.create(), config);

	}
}
