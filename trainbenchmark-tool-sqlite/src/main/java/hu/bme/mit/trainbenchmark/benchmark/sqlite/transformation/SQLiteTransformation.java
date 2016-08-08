package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation;

import java.io.IOException;
import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class SQLiteTransformation<TSqlMatch extends SqlMatch> extends SqlTransformation<TSqlMatch, SQLiteDriver> {

	protected SQLiteTransformation(final SQLiteDriver driver, final Optional<String> workspaceDir,
			final RailwayQuery query, final Scenario scenario) throws IOException {
		super(driver, workspaceDir, query, scenario);
	}

}
