package hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.github.jamm.MemoryMeter;

import hu.bme.mit.incqueryds.Transaction;
import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkQuery;
import hu.bme.mit.incqueryds.trainbenchmark.TrainbenchmarkReader;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfig;

public class IqdCoreDriver extends Driver {

	protected final TrainbenchmarkReader reader = new TrainbenchmarkReader();
	protected final IqdCoreBenchmarkConfig bc;
	protected final TransactionFactory transactionFactory;
	protected TrainbenchmarkQuery query;
	protected Transaction lastTransaction;

	public IqdCoreDriver(final IqdCoreBenchmarkConfig bc, final TransactionFactory transactionFactory) {
		super();
		this.bc = bc;
		this.transactionFactory = transactionFactory;
	}

	@Override
	public void read(final String modelPath) throws Exception {
		lastTransaction = newTransaction();
		reader.read(modelPath, lastTransaction);
	}

	@Override
	public String getPostfix() {
		return "-inferred.ttl";
	}

	public void setQuery(final TrainbenchmarkQuery query) {
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

	public String getQueryVariant() {
		return bc.getQueryVariant();
	}

	public void flushLastTransaction() {
		if (this.lastTransaction != null) {
			this.lastTransaction.close();
			this.lastTransaction = null;
		}
	}

	public void maybeMeasureMemory() {
		final String memPath = bc.getMemoryMeasurementPath();
		if (memPath != null) {
			final MemoryMeter meter = new MemoryMeter();
			final long memoryB = meter.measureDeep(query);
			final double memoryMB = memoryB / Math.pow(10, 6);
			final String line = String.join(",",
					Arrays.asList(bc.getToolName(), bc.getQueryVariant(), bc.getFileName(), String.format("%.02f", memoryMB))) + "\n";
			try {
				FileUtils.write(new File(memPath), line, true);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
}
