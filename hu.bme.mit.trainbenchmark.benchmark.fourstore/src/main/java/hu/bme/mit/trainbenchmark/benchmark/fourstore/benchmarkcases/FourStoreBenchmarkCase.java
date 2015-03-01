package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.FourStoreDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.List;

public class FourStoreBenchmarkCase extends AbstractBenchmarkCase<Long> {

	protected static final String CLUSTERNAME = "trainbenchmark_cluster";
	protected FourStoreBenchmarkConfig fsbc;

	@Override
	public void init() throws IOException {
		this.fsbc = (FourStoreBenchmarkConfig) bc;

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName()
				+ ".sparql";
		driver = new FourStoreDriver(RDFConstants.BASE_PREFIX, queryPath, CLUSTERNAME);
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
	public List<Long> check() throws IOException {
		results = driver.runQuery();
		return results;
	}

}
