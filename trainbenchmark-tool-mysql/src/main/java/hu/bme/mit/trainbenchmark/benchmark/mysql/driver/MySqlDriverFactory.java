package hu.bme.mit.trainbenchmark.benchmark.mysql.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class MySqlDriverFactory extends DriverFactory<MySqlDriver> {

	@Override
	public MySqlDriver createInstance() throws Exception {
		return new MySqlDriver();
	}

}
