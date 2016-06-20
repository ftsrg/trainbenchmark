package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair;

import java.io.IOException;
import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation.SQLiteTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SQLiteTransformationRepair<TMatch extends SqlMatch> extends SQLiteTransformation<TMatch> {

	protected SQLiteTransformationRepair(final SQLiteDriver driver, final Optional<String> workspaceDir, final RailwayQuery query) throws IOException {
		super(driver, workspaceDir, query, "Repair");
	}

}
