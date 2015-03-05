package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.FourStoreDriver;

import java.io.IOException;
import java.util.Collection;

public class FourStoreBenchmarkCase extends AbstractBenchmarkCase<Long> {

	protected FourStoreBenchmarkConfig fsbc;

	@Override
	public void init() throws IOException {
		this.fsbc = (FourStoreBenchmarkConfig) bc;

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName()
				+ ".sparql";
		driver = new FourStoreDriver(queryPath);
	}

	@Override
	public void read() throws IOException {
		final String modelPath = bc.getBenchmarkArtifact();
		driver.read(modelPath);
	}

	@Override
	public void destroy() throws IOException {
		driver.destroy();
	}

	@Override
	public Collection<Long> check() throws IOException {
		results = driver.runQuery();
		return results;
	}

}
