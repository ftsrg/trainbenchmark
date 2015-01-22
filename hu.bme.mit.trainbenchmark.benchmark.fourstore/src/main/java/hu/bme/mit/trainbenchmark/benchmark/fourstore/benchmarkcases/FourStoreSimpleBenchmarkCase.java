package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;

import eu.mondo.driver.fourstore.FourStoreGraphDriverReadWrite;

public abstract class FourStoreSimpleBenchmarkCase extends FourStoreBenchmarkCase {

	@Override
	public void init(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		this.fsbc = (FourStoreBenchmarkConfig) bc;
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		modelFilePath = bc.getBenchmarkArtifact();

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}

		// run 4store
		driver = new FourStoreGraphDriverReadWrite(CLUSTERNAME);
		driver.start();
	}

}
