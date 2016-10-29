package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfig;

public class IqdCoreDriverFactory extends DriverFactory<IqdCoreDriver> {

	protected IqdCoreBenchmarkConfig bc;
	protected TransactionFactory transactionFactory;

	public IqdCoreDriverFactory(final IqdCoreBenchmarkConfig bc, final TransactionFactory transactionFactory) {
		this.bc = bc;
		this.transactionFactory = transactionFactory;
	}
	
	@Override
	public IqdCoreDriver createInstance() throws Exception {
		return new IqdCoreDriver(bc, transactionFactory);
	}

}
