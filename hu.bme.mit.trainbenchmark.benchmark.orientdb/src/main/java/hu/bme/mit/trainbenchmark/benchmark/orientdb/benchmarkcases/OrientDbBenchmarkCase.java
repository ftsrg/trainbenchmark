package hu.bme.mit.trainbenchmark.benchmark.orientdb.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class OrientDbBenchmarkCase extends AbstractBenchmarkCase<Vertex> {
	
	protected OrientGraph graphDb;
	protected String dbPath;
	protected String query;
	
	protected OrientDbDriver orientDriver;
	
	@Override
	public void init() throws IOException {
		super.init();
		dbPath = bc.getWorkspacePath() + "/models/orient-dbs/railway-database";
		query = FileUtils.readFileToString(new File(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.orientdb/src/main/resources/queries/" + getName() + ".gremlin"));
	}
	
	@Override
	protected void read() throws FileNotFoundException, IOException {
		driver = orientDriver = new OrientDbDriver(dbPath, query);
		driver.read(bc.getBenchmarkArtifact());
		
		graphDb = orientDriver.getGraphDb();
	}

	@Override
	protected Collection<Vertex> check() throws IOException {
		results = driver.runQuery();
		return results;
	}
	
	@Override
	protected void destroy() throws IOException {
		driver.destroy();
	}

}
