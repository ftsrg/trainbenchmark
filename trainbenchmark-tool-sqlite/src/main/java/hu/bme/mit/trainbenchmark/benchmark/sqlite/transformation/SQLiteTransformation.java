package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation;

import java.io.IOException;
import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SQLiteTransformation<TObject> extends SqlTransformation<TObject, SQLiteDriver> {

	protected SQLiteTransformation(final SQLiteDriver driver, final Optional<String> workspaceDir,
			final RailwayQuery query, final String scenario) throws IOException {
		super(driver, workspaceDir, query, scenario);
	}

}
