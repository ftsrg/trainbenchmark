package hu.bme.mit.trainbenchmark.benchmark.sql.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.ConcreteMetric;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

public class SQLNumberOfNodesMetric extends ConcreteMetric<SQLDriver> {

	public SQLNumberOfNodesMetric(SQLDriver driver) {
		super(driver);
	}

	@Override
	public void calculate() {
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
