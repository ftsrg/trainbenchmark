package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation.SQLiteTransformation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SQLiteTransformationRepair<TMatch extends SqlMatch> extends SQLiteTransformation<TMatch> {

	protected SQLiteTransformationRepair(final SQLiteDriver driver, final BenchmarkConfigCore benchmarkConfig, final RailwayQuery query) throws IOException {
		super(driver, benchmarkConfig, query);
	}

}
