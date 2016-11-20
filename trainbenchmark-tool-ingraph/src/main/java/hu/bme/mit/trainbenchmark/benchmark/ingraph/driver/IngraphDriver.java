package hu.bme.mit.trainbenchmark.benchmark.ingraph.driver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.github.jamm.MemoryMeter;

import hu.bme.mit.ire.Transaction;
import hu.bme.mit.ire.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.config.IngraphBenchmarkConfig;
import ingraph.ire.IngraphAdapter;

public class IngraphDriver extends Driver {

	protected final IngraphBenchmarkConfig bc;
	protected final TransactionFactory transactionFactory;
	protected Transaction tx;
	protected IngraphAdapter adapter;

	public IngraphDriver(final IngraphBenchmarkConfig bc, final TransactionFactory transactionFactory) {
		super();
		this.bc = bc;
		this.transactionFactory = transactionFactory;
	}

	@Override
	public void read(final String modelPath) throws Exception {
		beginTransaction();
		adapter.readGraph(modelPath, tx);
		finishTransaction();
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
		tx = transactionFactory.newBatchTransaction();
		return tx;
	}

	@Override
	public void beginTransaction() {
		newTransaction();
	}

	@Override
	public void finishTransaction() throws Exception {
		if (tx != null) {
			tx.close();
			tx = null;
		}
	}

	public long newKey() {
		return transactionFactory.newKey();
	}

	public String getQueryVariant() {
		return bc.getQueryVariant();
	}

	public void maybeMeasureMemory() {
		final String memPath = bc.getMemoryMeasurementPath();
		if (memPath != null) {
			final MemoryMeter meter = new MemoryMeter();
			final long memoryB = meter.measureDeep(adapter.engine());
			final double memoryMB = memoryB / Math.pow(10, 6);
			final String line = String.join(",",
					Arrays.asList(bc.getToolName(), bc.getQueryVariant(), bc.getFileName(), String.format("%.02f", memoryMB))) + "\n";
			try {
				FileUtils.write(new File(memPath), line, Charset.defaultCharset(), true);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setAdapter(final IngraphAdapter adapter) {
		this.adapter = adapter;
	}
}
