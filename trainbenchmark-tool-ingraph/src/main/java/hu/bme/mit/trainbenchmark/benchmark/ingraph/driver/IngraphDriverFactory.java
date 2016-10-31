package hu.bme.mit.trainbenchmark.benchmark.ingraph.driver;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.config.IngraphBenchmarkConfig;

public class IngraphDriverFactory extends DriverFactory<IngraphDriver> {

	protected IngraphBenchmarkConfig bc;
	protected TransactionFactory transactionFactory;

	public IngraphDriverFactory(final IngraphBenchmarkConfig bc, final TransactionFactory transactionFactory) {
		this.bc = bc;
		this.transactionFactory = transactionFactory;
	}
	
	@Override
	public IngraphDriver createInstance() throws Exception {
		return new IngraphDriver(bc, transactionFactory);
	}

}
