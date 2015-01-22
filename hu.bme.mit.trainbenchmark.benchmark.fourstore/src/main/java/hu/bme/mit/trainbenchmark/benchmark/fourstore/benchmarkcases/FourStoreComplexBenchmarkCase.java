package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public abstract class FourStoreComplexBenchmarkCase extends FourStoreBenchmarkCase {

	protected List<Long> invalids;

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
		driver = new FourStoreGraphDriverReadWrite(CLUSTERNAME);
		driver.start();
	}
	
	@Override
	public void check() throws IOException {
		bmr.startStopper();
		invalids = driver.queryIds(sparqlQuery);
		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}
}
