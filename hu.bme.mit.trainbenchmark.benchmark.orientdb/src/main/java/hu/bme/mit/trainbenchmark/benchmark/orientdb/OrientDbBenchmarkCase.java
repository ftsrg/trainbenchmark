package hu.bme.mit.trainbenchmark.benchmark.orientdb;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.checkers.OrientDbChecker;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.OrientDbTransformation;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import java.io.IOException;
import java.util.Comparator;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.Vertex;

public class OrientDbBenchmarkCase extends AbstractBenchmarkCase<OrientDbMatch, Vertex> {

	protected OrientGraph graphDb;
	protected String dbPath;
	protected String benchmarkDir;

	protected OrientDbDriver orientDriver;

	@Override
	public void init() throws IOException {
		super.init();

		dbPath = bc.getWorkspacePath() + "models/orient-dbs/railway-database";
		benchmarkDir = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.orientdb";
		driver = orientDriver = new OrientDbDriver(dbPath, benchmarkDir);
		checker = OrientDbChecker.newInstance(orientDriver, bc.getQuery());

		if (bc.getScenario() != Scenario.BATCH) {
			transformation = OrientDbTransformation.newInstance(orientDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new OrientDbMatchComparator();
	}
	
}
