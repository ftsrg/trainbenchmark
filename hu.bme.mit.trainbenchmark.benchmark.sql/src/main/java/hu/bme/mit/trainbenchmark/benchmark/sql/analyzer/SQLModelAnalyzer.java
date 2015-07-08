package hu.bme.mit.trainbenchmark.benchmark.sql.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.NumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.sql.analyzer.metrics.SQLNumberOfEdgesMetric;
import hu.bme.mit.trainbenchmark.benchmark.sql.analyzer.metrics.SQLNumberOfNodesMetric;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;

public class SQLModelAnalyzer extends ModelAnalyzer<SQLDriver> {

	@Override
	public void initializeMetrics(SQLDriver driver) {
		NumberOfNodesMetric.instance().attachConcreteMetric(
				new SQLNumberOfNodesMetric(driver));
		NumberOfEdgesMetric.instance().attachConcreteMetric(
				new SQLNumberOfEdgesMetric(driver));

	}
}
