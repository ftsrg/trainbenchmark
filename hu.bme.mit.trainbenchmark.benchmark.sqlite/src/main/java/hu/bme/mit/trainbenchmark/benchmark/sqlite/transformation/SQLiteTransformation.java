package hu.bme.mit.trainbenchmark.benchmark.sqlite.transformation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SQLTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.inject.SQLiteTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair.SQLiteTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair.SQLiteTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SQLiteTransformation<TObject> extends SQLTransformation<TObject> {

	protected SQLiteTransformation(final SQLiteDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		super(driver, benchmarkConfig, query);

		final String updatePath = getTransformationDirectory() + benchmarkConfig.getScenarioName() + query + ".sql";
		updateQuery = FileUtils.readFileToString(new File(updatePath));
	}
	
	protected String getTransformationDirectory() {
		return benchmarkConfig.getWorkspacePath() + "hu.bme.mit.trainbenchmark.benchmark.sqlite/" + "src/main/resources/transformations/";
	}
	
	public static ModelTransformation<?, ?> newInstance(final SQLiteDriver driver, final BenchmarkConfig benchmarkConfig, final RailwayQuery query) throws IOException {
		switch (benchmarkConfig.getScenario()) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new SQLiteTransformationRepairConnectedSegments(driver, benchmarkConfig, query);				
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
