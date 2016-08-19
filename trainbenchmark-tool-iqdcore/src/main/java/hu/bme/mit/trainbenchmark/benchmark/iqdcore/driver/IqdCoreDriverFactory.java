package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper;

public class IqdCoreDriverFactory extends DriverFactory<IqdCoreDriver> {

	protected IqdCoreBenchmarkConfigWrapper bcw;
	protected TransactionFactory transactionFactory;

	public IqdCoreDriverFactory(final IqdCoreBenchmarkConfigWrapper bcw, final TransactionFactory transactionFactory) {
		this.bcw = bcw;
		this.transactionFactory = transactionFactory;
	}
	
	@Override
	public IqdCoreDriver createInstance() throws Exception {
		return new IqdCoreDriver(bcw, transactionFactory);
	}

}
