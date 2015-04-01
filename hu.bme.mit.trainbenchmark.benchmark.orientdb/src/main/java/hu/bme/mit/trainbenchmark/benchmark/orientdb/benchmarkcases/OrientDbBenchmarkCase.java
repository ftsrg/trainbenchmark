package hu.bme.mit.trainbenchmark.benchmark.orientdb.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbBenchmarkCase extends AbstractBenchmarkCase<Vertex> {
	
	@Override
	public void init() throws IOException {
		final String dbPath = bc.getWorkspacePath() + "/models/orient-dbs/railway-database";
		final String query = FileUtils.readFileToString(new File(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.orientdb/src/main/resources/queries/" + getName() + ".gremlin"));
		driver = new OrientDbDriver(dbPath, query);
	}
	
	@Override
	protected void read() throws FileNotFoundException, IOException {
		final String modelPath = bc.getBenchmarkArtifact();
		driver.read(modelPath);
	}

	@Override
	protected Collection<Vertex> check() throws IOException {
		results = driver.runQuery();
		return results;
	}

}
