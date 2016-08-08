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
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IqdCoreBenchmarkConfigWrapper;

public class IqdCoreDriver extends Driver<Long> {

	protected final TransactionFactory transactionFactory;
	protected final TrainbenchmarkReader reader;
	private TrainbenchmarkQuery query;
	private Transaction lastTransaction;
    private IqdCoreBenchmarkConfigWrapper config;
	public IqdCoreDriver(final IqdCoreBenchmarkConfigWrapper config,
						 final TransactionFactory input) {
		super();
		this.transactionFactory = input;
		this.reader = new TrainbenchmarkReader();
		this.config = config;
	}

	@Override
	public void read(final String modelPath) throws Exception {
		this.lastTransaction = newTransaction();
		reader.read(modelPath, lastTransaction);
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
	
	public String getQueryVariant() {
		return config.getQueryVariant();
	}

	public void flushLastTransaction() {
		if (this.lastTransaction != null) {
			this.lastTransaction.close();
			this.lastTransaction = null;
		}
	}

	public void maybeMeasureMemory() {
		String memPath = config.getMemoryMeasurementPath();
		if (memPath != null) {
			final MemoryMeter meter = new MemoryMeter();
			long memoryB = meter.measureDeep(query);
			double memoryMB = memoryB / Math.pow(10, 6);
			String line = String.join(",",
				Arrays.asList(
				config.getToolName(),
				config.getQueryVariant(),
				config.getFileName(),
				String.format("%.02f", memoryMB)
				)) + "\n";
			try {
				FileUtils.write(new File(memPath), line, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
