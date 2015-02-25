package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.FourStoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public class FourStoreBenchmarkCase extends AbstractTransformationBenchmarkCase<Long> {

	protected static final String CLUSTERNAME = "trainbenchmark_cluster";

	protected FourStoreBenchmarkConfig fsbc;

	protected String modelFilePath;
	protected String sparqlFilePath;
	protected String sparqlQuery;

	protected FourStoreGraphDriverReadWrite fsDriver;

	public String getResourceDirectory() {
		return bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/";
	}

	@Override
	public void read() throws IOException {
		try {
			fsDriver.load(modelFilePath);
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}
		driver = new FourStoreDriver(fsDriver);
	}

	@Override
	public void destroy() throws IOException {
		fsDriver.stop();
	}

	@Override
	public List<Long> check() throws IOException {
		results = fsDriver.queryIds(sparqlQuery);
		return results;
	}
	
	@Override
	public void init() throws IOException {
		this.fsbc = (FourStoreBenchmarkConfig) bc;

		modelFilePath = bc.getBenchmarkArtifact();

		sparqlFilePath = getResourceDirectory() + "/queries/" + getName() + ".sparql";
		sparqlQuery = FileUtils.readFileToString(new File(sparqlFilePath));

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}

		// run 4store
		fsDriver = new FourStoreGraphDriverReadWrite(CLUSTERNAME);
		fsDriver.start();
	}
	
}
