package hu.bme.mit.trainbenchmark.benchmark.ingraph.driver;

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.ire.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.config.IngraphBenchmarkConfig;
import ingraph.ire.IngraphAdapter;

public class IngraphDriver extends Driver {

	protected final IngraphBenchmarkConfig bc;
	protected final TransactionFactory transactionFactory;
	protected Transaction lastTransaction;
	private IngraphAdapter adapter;

	public IngraphDriver(final IngraphBenchmarkConfig bc, final TransactionFactory transactionFactory) {
		super();
		this.bc = bc;
		this.transactionFactory = transactionFactory;
	}

	@Override
	public void read(final String modelPath) throws Exception {
		adapter.readGraph(modelPath, newTransaction());
	}

	@Override
	public String getPostfix() {
		return "-tinkerpop.graphml";
	}


	@Override
	public void destroy() {
		if (adapter != null) {
			adapter.engine().shutdown();
		}
	}

	public Transaction newTransaction() {
		lastTransaction = transactionFactory.newBatchTransaction();
		return lastTransaction;
	}

	public long newKey() {
		return transactionFactory.newKey();
	}

	public String getQueryVariant() {
		return bc.getQueryVariant();
	}

	public void flushLastTransaction() {
		if (this.lastTransaction != null) {
			this.lastTransaction.close();
			this.lastTransaction = null;
		}
	}

//	public void maybeMeasureMemory() {
//		final String memPath = bc.getMemoryMeasurementPath();
//		if (memPath != null) {
//			final MemoryMeter meter = new MemoryMeter();
//			final long memoryB = meter.measureDeep(adapter.engine());
//			final double memoryMB = memoryB / Math.pow(10, 6);
//			final String line = String.join(",",
//					Arrays.asList(bc.getToolName(), bc.getQueryVariant(), bc.getFileName(), String.format("%.02f", memoryMB))) + "\n";
//			try {
//				FileUtils.write(new File(memPath), line, Charset.defaultCharset(), true);
//			} catch (final IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public void setAdapter(final IngraphAdapter adapter) {
		this.adapter = adapter;
	}
}
