package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation;

import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class SQLiteTransformation<TSqlMatch extends SqlMatch> extends SqlTransformation<TSqlMatch, SQLiteDriver> {

	protected SQLiteTransformation(final SQLiteDriver driver, final String workspaceDir, final RailwayOperation operation)
			throws IOException {
		super(driver, workspaceDir, operation);

		// if there is a different transformation for SQLite, load that instead of the generic SQL one
		final String updatePath = workspaceDir + driver.getSQLiteResourceDirectory() + "transformations/" + operation + "Rhs.sql";
		final File file = new File(updatePath);
		if (file.exists()) {
			this.updateQuery = FileUtils.readFileToString(new File(updatePath), StandardCharsets.UTF_8);
		}
	}

}
