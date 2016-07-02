package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import java.util.List;

import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.incqueryds.Transaction;
import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkReader;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

public class IQDCoreDriver extends Driver<Long> {

	protected final TransactionFactory transactionFactory;
	protected final TrainbenchmarkReader reader;
	protected final String variant;
	private TrainbenchmarkQuery query;
	private Transaction lastTransaction;

	public IQDCoreDriver(final String variant, final TransactionFactory input) {
		super();
		this.transactionFactory = input;
		this.reader = new TrainbenchmarkReader();
		this.variant = variant;
	}

	@Override
	public void read(final String modelPath) throws Exception {
		this.lastTransaction = newTransaction();
		reader.read(modelPath, lastTransaction);
	}
	@Override
	public List<Long> collectVertices(final String type) throws Exception {
		return null;
	}

	@Override
	public String getPostfix() {
		return "-inferred.ttl";
	}

	public void setQuery(TrainbenchmarkQuery query) {
		this.query = query;
	}
	@Override
	public void destroy() {
		query.shutdown();
	}

	public Transaction newTransaction() {
		lastTransaction = transactionFactory.newBatchTransaction();
		return lastTransaction;
	}

	public long newKey() {
		return transactionFactory.newKey();
	}
	
	public String getVariant() {
		return variant;
	}

	public void flushLastTransaction() {
		if (this.lastTransaction != null) {
			this.lastTransaction.close();
			this.lastTransaction = null;
		}
	}
}
