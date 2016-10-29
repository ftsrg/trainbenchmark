package hu.bme.mit.trainbenchmark.benchmark.sqlite;

import hu.bme.mit.trainbenchmark.benchmark.comparators.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.operations.SQLiteModelOperationFactory;

public class SQLiteBenchmarkScenario extends BenchmarkScenario<SqlMatch, SQLiteDriver, SQLiteBenchmarkConfig> {

	public SQLiteBenchmarkScenario(final SQLiteBenchmarkConfig bc) throws Exception {
		super(new SQLiteDriverFactory(), new SQLiteModelOperationFactory(), new LongMatchComparator<SqlMatch>(), bc);
	}

}
