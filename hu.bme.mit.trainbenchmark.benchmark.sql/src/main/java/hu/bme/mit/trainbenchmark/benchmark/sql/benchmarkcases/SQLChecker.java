package hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.match.SQLMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class SQLChecker extends Checker<SQLMatch> {

	protected final SQLDriver driver;
	protected final Query query;
	protected final String queryDefinition;

	public SQLChecker(final SQLDriver driver, final BenchmarkConfig bc) throws IOException {
		super();
		this.driver = driver;
		this.query = bc.getQuery();

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.sql/src/main/resources/queries/" + bc.getQuery()
				+ ".sql";
		this.queryDefinition = FileUtils.readFileToString(new File(queryPath));
	}

	@Override
	public Collection<SQLMatch> check() throws IOException {
		return driver.runQuery(query, queryDefinition);
	}

}
