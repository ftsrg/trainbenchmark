package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SQLTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.inject.SQLiteTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair.SQLiteTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SQLiteTransformation {

	public static Transformation<?, ?> newInstance(final SQLDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		switch (benchmarkConfig.getScenario()) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLiteTransformationInjectConnectedSegments(driver, benchmarkConfig, query);				
			case SWITCHSENSOR:
				return new SQLiteTransformationRepairSwitchSensor(driver, benchmarkConfig, query);
			default:
				break;
			}
		case INJECT:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLiteTransformationInjectConnectedSegments(driver, benchmarkConfig, query);
			default:
				break;
			}
		}
		return SQLTransformation.newInstance(driver, benchmarkConfig, query);
	}
	
}
