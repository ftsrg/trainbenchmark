package hu.bme.mit.trainbenchmark.benchmark.sqlite;

import hu.bme.mit.trainbenchmark.benchmark.comparators.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.config.SQLiteBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.operations.SQLiteModelOperationFactory;

public class SQLiteBenchmarkScenario extends BenchmarkScenario<SqlMatch, SQLiteDriver, SQLiteBenchmarkConfigWrapper> {

	public SQLiteBenchmarkScenario(final SQLiteBenchmarkConfigWrapper sqlbcw) throws Exception {
		super(SQLiteDriver.create(), SQLiteModelOperationFactory.create(), LongMatchComparator.create(), sqlbcw);
	}

}
