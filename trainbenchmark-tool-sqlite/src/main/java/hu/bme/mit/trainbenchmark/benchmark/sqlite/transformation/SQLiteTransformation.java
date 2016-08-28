package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class SQLiteTransformation<TSqlMatch extends SqlMatch> extends SqlTransformation<TSqlMatch, SQLiteDriver> {

	protected SQLiteTransformation(final SQLiteDriver driver, final String workspaceDir, final RailwayQuery query, final Scenario scenario)
			throws IOException {
		super(driver, workspaceDir, query, scenario);

		// if there is a different transformation for SQLite, load that instead of the generic SQL one
		final String updatePath = workspaceDir + "/trainbenchmark-tool-sqlite/src/main/resources/" + "transformations/" + scenario + query + ".sql";
		final File file = new File(updatePath);
		if (file.exists()) {
			this.updateQuery = FileUtils.readFileToString(new File(updatePath));
		}
	}

}
