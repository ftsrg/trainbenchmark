package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public abstract class FourStoreBenchmarkCase implements BenchmarkCase {

	protected static final String CLUSTERNAME = "trainbenchmark_cluster";

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected FourStoreBenchmarkConfig fsbc;
	protected List<Long> invalids;

	protected String modelFilePath;
	protected String sparqlFilePath;
	protected String sparqlQuery;

	protected FourStoreGraphDriverReadWrite driver;

	@Override
	public String getTool() {
		return "4store" + (fsbc == null || !fsbc.isRamdisk() ? "" : "-ramdisk");
	}

	public String getResourceDirectory() {
		return bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.fourstore/src/main/resources/";
	}

	@Override
	public void init(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		this.fsbc = (FourStoreBenchmarkConfig) bc;
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		modelFilePath = bc.getBenchmarkArtifact();

		sparqlFilePath = getResourceDirectory() + "/queries/" + getName() + ".sparql";
		sparqlQuery = FileUtils.readFileToString(new File(sparqlFilePath));

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}

		// run 4store
		final String connectionString = "fourstore://" + CLUSTERNAME;
		driver = new FourStoreGraphDriverReadWrite(connectionString);
		driver.start();
	}

	@Override
	public void load() throws IOException {
		bmr.startStopper();
		try {
			driver.load(modelFilePath);
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}
		bmr.setReadTime();
	}

	@Override
	public void check() throws IOException {
		bmr.startStopper();
		invalids = driver.queryIds(sparqlQuery);
		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

	@Override
	public void measureMemory() throws IOException {
		// Util.runGC();
		// final Memory memory = new Memory();
		//
		// final String getMemoryCommand = getResourceDirectory() +
		// "/scripts/4s-memory.sh";
		// final Process child = Runtime.getRuntime().exec(getMemoryCommand);
		//
		// final InputStream in = child.getInputStream();
		// final BufferedReader br = new BufferedReader(new
		// InputStreamReader(in));
		//
		// final String vmRssStr = br.readLine();
		// memory.setRss(Integer.parseInt(vmRssStr));
		//
		// in.close();
		//
		// bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() -
		// Runtime.getRuntime().freeMemory() +
		// memory.getMemory()
		// * 1024);
		bmr.addMemoryBytes(0);

	}

	@Override
	public void destroy() throws IOException {
		driver.stop();
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}
	
	@Override
	public int getResultSize() {
		return invalids.size();
	}


}
