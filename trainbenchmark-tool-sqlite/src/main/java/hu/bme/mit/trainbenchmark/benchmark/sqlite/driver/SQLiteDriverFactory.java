package hu.bme.mit.trainbenchmark.benchmark.sqlite.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class SQLiteDriverFactory extends DriverFactory<SQLiteDriver> {

	@Override
	public SQLiteDriver createInstance() throws Exception {
		return new SQLiteDriver();
	}

}
