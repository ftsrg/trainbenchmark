package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper;

public class IqdCoreDriverFactory extends DriverFactory<IqdCoreDriver> {

	protected IqdCoreBenchmarkConfigWrapper config;
	protected TransactionFactory transactionFactory;

	public IqdCoreDriverFactory(final IqdCoreBenchmarkConfigWrapper config, final TransactionFactory transactionFactory) {
		this.config = config;
		this.transactionFactory = transactionFactory;
	}
	
	@Override
	public IqdCoreDriver createInstance() throws Exception {
		return new IqdCoreDriver(config, transactionFactory);
	}

}
