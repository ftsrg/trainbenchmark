package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.io.IOException;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public abstract class FourStoreBenchmarkCase implements BenchmarkCase {

	protected static final String CLUSTERNAME = "trainbenchmark_cluster";

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected FourStoreBenchmarkConfig fsbc;

	protected String modelFilePath;
	protected String sparqlFilePath;
	protected String sparqlQuery;

	protected FourStoreGraphDriverReadWrite driver;

	@Override
	public String getTool() {
		return "4store";
	}

	public String getResourceDirectory() {
		return bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/";
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
	public void measureMemory() throws IOException {

	}

	@Override
	public void destroy() throws IOException {
		driver.stop();
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}
	
}
